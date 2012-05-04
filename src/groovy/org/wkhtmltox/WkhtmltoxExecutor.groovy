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
        commandList.add(0,binaryPath)
        commandList.add("-q")
        commandList.add("-")
        commandList.add("-")

        ProcessBuilder builder = new ProcessBuilder(commandList);
        final Process process = builder.start();

        OutputStreamWriter os = new OutputStreamWriter(process.getOutputStream())
        os.write(html)
        os.close()


            System.out.print("asd")
        String error = ""
        process.getErrorStream().withReader {
            System.out.print(it.readLine())
            //error << it.readLine()
        }

        process.getErrorStream().close()
        if(error && !error.empty){
            throw new WkhtmltoxException(error)
        }


        return IOUtils.toByteArray(process.getInputStream())
    }
}
