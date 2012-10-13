class WkhtmltoxGrailsPlugin {
    def version = "0.1.6"
    def grailsVersion = "1.3.6 > *"
    def loadAfter = ['mail','controllers']
    def author = "Tobias Nendel"
    def authorEmail = "tobias.nendel@scubical.com"
    def title = "Wkhtmltopdf Plugin"
    def description = '''Provides a Wrapper for wkhtmltopdf,
a Simple shell utility to convert html to pdf using the webkit rendering engine, and qt.

        render( filename:"Filename ${filename}.pdf",view:"/path_to_gsp",
                model:[someModel:someModel],
                header:"/path_to_gsp",
                footer:"/path_to_gsp",
                marginLeft:20,
                marginTop:30,
                marginBottom:20,
                marginRight:20,
                headerSpacing:10
        )
'''

    def observe = ['controllers']
    def documentation = "http://grails.org/plugin/wkhtmltox"

    def license = "APACHE"

    def doWithDynamicMethods = {ctx ->
        // hooking into render method
        application.controllerClasses.each {controllerClass ->
            replaceRenderMethod(controllerClass, ctx)
        }
    }

    def onChange = {event ->
        // only process controller classes
        if (application.isControllerClass(event.source)) {
            def clazz = application.getControllerClass(event.source?.name)
            replaceRenderMethod(clazz, event.ctx)
        }
    }

    /**
     * This implementation is based on Marc Palmers feed plugin. It hooks into the render method
     * of a Grails controller class and adds an alternative behaviour for the mime type
     * 'text/calendar' used by the iCalendar plugin.
     */
    private void replaceRenderMethod(controllerClass, ctx) {
        def oldRender = controllerClass.metaClass.pickMethod("render", [Map] as Class[])
        controllerClass.metaClass.render = {Map params ->
            if (params.contentType?.toLowerCase() == 'application/x-pdf' || response.format == "pdf") {
                def filename = params.remove("filename")

                def data = ctx.wkhtmltoxService.makePdf(params)

                response.setHeader("Content-disposition", "attachment; filename=${filename}")
                response.contentType = "application/x-pdf"
                response.outputStream.write(data)
                response.characterEncoding = 'UTF-8'
                response.setHeader('Cache-Control', 'no-store, no-cache, must-revalidate') //HTTP/1.1
                response.setHeader('Pragma', 'no-cache') // HTTP/1.0
                response.outputStream.flush()

            } else {
                // Defer to original render method
                oldRender.invoke(delegate, [params] as Object[])
            }
        }
    }
}
