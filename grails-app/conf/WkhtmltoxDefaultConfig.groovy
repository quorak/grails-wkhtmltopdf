
/**
 * Created by IntelliJ IDEA.
 * User: tobiasnendel
 * Date: 18.04.11
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
grails {
    plugin {
        wkhtmltox {
            binary = "/usr/bin/wkhtmltopdf"
            makeBinaryAvailableClosure = { String targetPath ->

                Runtime run = Runtime.getRuntime();
                Process pr = run.exec("curl -O http://wkhtmltopdf.googlecode.com/files/wkhtmltopdf-0.11.0_rc1-static-amd64.tar.bz2");

                pr.getInputStream().eachLine {
                    println it.toString()
                }
                pr = run.exec("tar xjvf wkhtmltopdf-0.11.0_rc1-static-amd64.tar.bz2");

                pr.getInputStream().eachLine {
                    println it.toString()
                }

                pr = run.exec("chmod +x wkhtmltopdf-amd64");

                pr.getInputStream().eachLine{
                    println it.toString()
                }
                pr = run.exec("pwd");

                pr.getInputStream().eachLine {
                    println it.toString()
                }
            }
        }
    }
}
