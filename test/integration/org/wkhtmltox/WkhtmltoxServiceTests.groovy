package org.wkhtmltox

class WkhtmltoxServiceTests extends GroovyTestCase {

	WkhtmltoxService wkhtmltoxService

	def grailsApplication

	void testSomething() {

		grailsApplication.config.grails.plugins.wkhtmltox.binary = "/bin/asd"

		WkhtmltoxWrapper wrapper = new WkhtmltoxWrapper()
		wrapper.marginLeft = 5
		wrapper.marginRight = 5
		wrapper.marginTop = 5
		wrapper.marginBottom = 5

		PartialView view = new PartialView("/test/test",[])

		wkhtmltoxService.makePdf(wrapper,view)
	}
}
