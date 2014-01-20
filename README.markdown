Grails Wkhtmltox / Wkhtmltopdf 
=========================

This plugin provides an easy integration of the wkthmltox library into Grails.

Wkhtmltopdf
----------------
"Simple shell utility to convert html to pdf using the webkit rendering engine, and qt."
http://code.google.com/p/wkhtmltopdf/
GNU Lesser GPL

Installation
----------------

mac

    brew install wkhtmltopdf

linux

    apt-get install wkhtmltopdf

see: http://code.google.com/p/wkhtmltopdf/wiki/compilation
compiled versions available at: http://code.google.com/p/wkhtmltopdf/downloads/list

finally make sure the following command works as expected:

    wkhtmltopdf www.google.de test.pdf
    

Configuration
----------------

put the following line into your Config.groovy and adjust the path to your wkhtmltox binary ( which wkhtmltopdf )

unix

    grails.plugin.wkhtmltox.binary = "/usr/bin/wkhtmltopdf"
    
windows
    
    grails.plugin.wkhtmltox.binary = "C:/local/wkhtmltopdf/wkhtmltopdf.exe"
    

also add the pdf mime type to Config.groovy

    grails.mime.types = [
        all:           '*/*',
        .....
       pdf:         'application/x-pdf'
    ]
    


Usage
----------------

to stream the content of an controller-action as pdf just call: /context/some/someAction.pdf

    class SomerController {
        def someAction = {
            def someInstance = SomeDomainObject.get(params.id)
    
            render( filename:"File ${someInstance.id}.pdf",
					view:"/some/someGspTemplate",
                    model:[someInstance:someInstance],
                    header:"/pdf/someHeader",
                    footer:"/pdf/someFooter",
                    marginLeft:20,
                    marginTop:35,
                    marginBottom:20,
                    marginRight:20,
                    headerSpacing:10,
            )
        }
    }

Or create binary pdf data and use them for any other purpose

    class SomeService implements Serializable {
    
    		def byte[] pdfData = wkhtmltoxService.makePdf(
                    view: "/pdf/someGspTemplate",
                    model: [someInstance: someInstance],
                    header: "/pdf/someHeader",
                    footer: "/pdf/someFooter",
                    marginLeft: 20,
                    marginTop: 35,
                    marginBottom: 20,
                    marginRight: 20,
                    headerSpacing: 10,
            )
    	
    	
    		// DO Something e.g. send as mail
    		//sendAsynchronousMail {
            //    multipart true
            //    to "mail@mail.de"
            //    subject "see PDF Attachment";
            //    attachBytes "PDF Attachment.pdf", "application/x-pdf", pdfData
            //    body "see my pdf attachment"
            //}
        }
    }

write your gsps as usual, just make sure, that the url's to the resources are absolute and reachable by the host maschine

	<link rel="stylesheet" href="${resource(dir: '/css/style.css', absolute: true)}" type="text/css"/>
	<img src="${resource(dir: '/images/image.jpg', absolute: true)}" width="200px"/>

Options
----------------

see the following command for all options available:

	wkhtmltopdf --extended-help
		

Known issues
----------------

* wkhtmltox must work ( try: ```wkhtmltopdf www.myhomepage.com myhomepage.pdf``` see: http://code.google.com/p/wkhtmltopdf/wiki/Usage )
* not tested on Windows (except windows7)
