package org.wkhtmltox

import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU

import grails.util.GrailsWebUtil
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import grails.plugin.mail.MailMessageContentRender
import grails.plugin.mail.MailMessageContentRenderer
import javax.servlet.http.HttpServletRequest

class WkhtmltoxService {

    static transactional = true
    def config = ConfigurationHolder.config.grails.plugins.wkhtmltox
    def mailMessageContentRenderer

    def byte[] makePdf(config) {

        WkhtmltoxWrapper wrapper = new WkhtmltoxWrapper()

        def view = config.remove("view")
        def model = config.remove("model")
        def plugin = config.remove("plugin")
        def header = config.remove("header")
        def footer = config.remove("footer")

        config.encoding = config.encoding ? config.encoding : "UTF-8";

        PartialView contentPartial = new PartialView(view,model,plugin)
        PartialView headerPartial
        PartialView footerPartial

        if(header){
            headerPartial = new PartialView(header,model,plugin)
        }
        if(footer){
            footerPartial = new PartialView(footer,model,plugin)
        }

        config.each{ key,value ->
            wrapper."$key" = value
        }


        return makePdf(wrapper,contentPartial,headerPartial,footerPartial)

    }

    def byte[] makePdf(WkhtmltoxWrapper wrapper,contentPartial,headerPartial = null,footerPartial = null) {

        String htmlBodyContent =        renderMailView(contentPartial)

        if(headerPartial){
            File headerFile =           makePartialViewFile(headerPartial)
            wrapper.headerHtml =        "file://" + headerFile.absolutePath
        }
        if(footerPartial){
            File footerFile =           makePartialViewFile(footerPartial)
            wrapper.footerHtml =        "file://" + footerFile.absolutePath
        }

        WkhtmltoxExecutor wkhtmltoxExecutor = new WkhtmltoxExecutor(config.binary.toString(),wrapper)

        return wkhtmltoxExecutor.generatePdf(htmlBodyContent)

    }






    protected String renderMailView(PartialView partialView) {
        return mailMessageContentRenderer.render(new StringWriter(), partialView.viewName, partialView.model, null, partialView.pluginName).out.toString();
    }

    File makePartialViewFile(PartialView pv){
        String content = renderMailView(pv)
        File tempFile = File.createTempFile("/wkhtmltopdf",".html")
        tempFile.withWriter {
            it.write(content)
            it.close()
        }
        tempFile.setReadable(true,true)
        tempFile.setWritable(true,true)
        return tempFile
    }


}
