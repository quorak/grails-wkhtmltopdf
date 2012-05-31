package org.wkhtmltox

import org.apache.commons.io.IOUtils
import org.apache.jasper.tagplugins.jstl.core.Url
import org.apache.commons.io.CopyUtils

/**
 * Created by IntelliJ IDEA.
 * User: tobiasnendel
 * Date: 19.04.11
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
class WkhtmltoxExecutor {

    String binaryPath
    WkhtmltoxWrapper wrapper


    WkhtmltoxExecutor(String binaryPath, WkhtmltoxWrapper wrapper) {


        if(!(new File(binaryPath)).exists()){
            throw new WkhtmltoxException("Could not locate Wkhtmltox binary.")
        }
        if(!wrapper){
            throw new WkhtmltoxException("Wrapper must be set.")
        }

        this.binaryPath = binaryPath
        this.wrapper = wrapper
    }

    byte[] generatePdf(URL url){

    } // TODO

    byte[] generatePdf(String html){



        def commandList = wrapper.toArgumentsList()
        byte[] data
        commandList.add(0,binaryPath)
        commandList.add("-q")
        commandList.add("-")
        commandList.add("-")

        System.out.print(commandList.toString())
        ProcessBuilder builder = new ProcessBuilder(commandList);
        final Process process = builder.start();

        OutputStreamWriter os = new OutputStreamWriter(process.getOutputStream())
        os.write(html)
        os.close()

        try{
            data = IOUtils.toByteArray(process.getInputStream())
        }catch(Exception e) {
            throw new WkhtmltoxException(e)
        }finally{
            process.getInputStream().close()
            String error = ""
            process.getErrorStream().withReader {
                System.out.print(it.readLine())
                error << it.readLine()
            }
            process.getErrorStream().close()
        }
        return data
    }
}
