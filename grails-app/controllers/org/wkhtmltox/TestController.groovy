package org.wkhtmltox

class TestController {

    def wkhtmltoxService

    def index = {

        response.setHeader("Content-disposition", "attachment; filename=xxx.pdf")
        response.contentType = "application/x-pdf"


        response.outputStream << wkhtmltoxService.makePdf()
        response.outputStream.flush()
        System.out.println("flused");


    }
}
