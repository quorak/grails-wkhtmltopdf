package org.wkhtmltox

import org.springframework.web.context.request.RequestContextHolder
import org.codehaus.groovy.grails.commons.GrailsResourceUtils
import org.springframework.web.context.support.WebApplicationContextUtils
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.plugins.PluginManagerHolder
import grails.util.GrailsWebUtil
import org.codehaus.groovy.grails.web.servlet.DefaultGrailsApplicationAttributes
import javax.servlet.http.HttpServletRequest
import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class WkhtmltoxService {

    static transactional = true
    def config = ConfigurationHolder.config.grails.plugins.wkhtmltox

    def byte[] makePdf(config) {

        WkhtmltoxWrapper wrapper = new WkhtmltoxWrapper()

        def view = config.remove("view")
        def model = config.remove("model")
        def plugin = config.remove("plugin")
        def header = config.remove("header")
        def footer = config.remove("footer")

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

        WkhtmltoxExecutor wkhtmltoxExecutor = new WkhtmltoxExecutor(config.binary,wrapper)

        return wkhtmltoxExecutor.generatePdf(htmlBodyContent)

    }










    File makePartialViewFile(PartialView pv){
        makePartialViewFile(pv.viewName,pv.model,pv.pluginName)
    }
    File makePartialViewFile(templateName,model,pluginName){
        String content = renderMailView(templateName,model,pluginName)
        File tempFile = File.createTempFile("/wkhtmltopdf",".html")
        tempFile.withWriter {
            it.write(content)
            it.close()
        }
        tempFile.setReadable(true,true)
        tempFile.setWritable(true,true)
        return tempFile
    }




















    protected String renderMailView(PartialView partialView) {
        renderMailView(partialView.viewName,partialView.model,partialView.pluginName)
    }


    def groovyPagesTemplateEngine;
    // Source from ru.perm.kefir.asynchronousmail.AsynchronousMailMessageBuilder in plugin async-mail Plugin
    static PATH_TO_MAILVIEWS = "/WEB-INF/grails-app/views"
    protected String renderMailView(templateName, model, pluginName = null) {
        if(!groovyPagesTemplateEngine) throw new IllegalStateException("Property [groovyPagesTemplateEngine] must be set!")
        assert templateName

        def engine = groovyPagesTemplateEngine
        def requestAttributes = RequestContextHolder.getRequestAttributes()
        boolean unbindRequest = false

        // outside of an executing request, establish a mock version
        if(!requestAttributes) {
            def servletContext  = ServletContextHolder.getServletContext()
            def applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
            requestAttributes = GrailsWebUtil.bindMockWebRequest(applicationContext)
            unbindRequest = true
        }
        def request = requestAttributes.request

        // See if the application has the view for it
        def uri = getMailViewUri(templateName, request)

        def r = engine.getResourceForUri(uri)
        // Try plugin view if not found in application
        if (!r || !r.exists()) {
            if (log.debugEnabled) {
                log.debug "Could not locate email view ${templateName} at ${uri}, trying plugin"
            }
            if (pluginName) {
                // Caution, this uses views/ always, whereas our app view resolution uses the PATH_TO_MAILVIEWS which may in future be orthogonal!
                def plugin = PluginManagerHolder.pluginManager.getGrailsPlugin(pluginName)
                String pathToView = null
                if (plugin) {
                    pathToView = '/plugins/'+GCU.getScriptName(plugin.name)+'-'+plugin.version+'/'+GrailsResourceUtils.GRAILS_APP_DIR+'/views'
                }

                if (pathToView != null) {
                    uri = GrailsResourceUtils.WEB_INF +pathToView +templateName+".gsp";
                    r = engine.getResourceForUri(uri)
                } else {
                    if (log.errorEnabled) {
                        log.error "Could not locate email view ${templateName} in plugin [$pluginName]"
                    }
                    throw new IllegalArgumentException("Could not locate email view ${templateName} in plugin [$pluginName]")
                }
            } else {
                if (log.errorEnabled) {
                    log.error "Could not locate email view ${templateName} at ${uri}, no pluginName specified so couldn't look there"
                }
                throw new IllegalArgumentException("Could not locate mail body ${templateName}. Is it in a plugin? If so you must pass the plugin name in the [plugin] variable")
            }
        }
        def t = engine.createTemplate(r)

        def out = new StringWriter();
        def originalOut = requestAttributes.getOut()
        requestAttributes.setOut(out)
        try {
            if(model instanceof Map) {
                t.make( model ).writeTo(out)
            } else {
                t.make().writeTo(out)
            }
        } finally {
            requestAttributes.setOut(originalOut)
            if(unbindRequest) {
                RequestContextHolder.setRequestAttributes(null)
            }
        }

        return out.toString()
    }
    protected String getMailViewUri(String viewName, HttpServletRequest request) {
        def buf = new StringBuilder(PATH_TO_MAILVIEWS)

        if(viewName.startsWith("/")) {
           def tmp = viewName[1..-1]
           if(tmp.indexOf('/') > -1) {
               def i = tmp.lastIndexOf('/')
               buf << "/${tmp[0..i]}/${tmp[(i+1)..-1]}"
           }
           else {
               buf << "/${viewName[1..-1]}"
           }
        } else {
           if (!request) throw new IllegalArgumentException(
               "Mail views cannot be loaded from relative view paths where there is no current HTTP request")
           def grailsAttributes = new DefaultGrailsApplicationAttributes(request.servletContext)
           buf << "${grailsAttributes.getControllerUri(request)}/${viewName}"
        }
        return buf.append(".gsp").toString()
    }
}
