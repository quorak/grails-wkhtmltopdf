package org.wkhtmltox

import grails.test.*
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class WkhtmltoxServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }


    WkhtmltoxService wkhtmltoxService


    void testSomething() {

        CH.config.grails.plugins.wkhtmltox.binary = "/bin/asd"

        WkhtmltoxWrapper wrapper = new WkhtmltoxWrapper()
        wrapper.marginLeft = 5
        wrapper.marginRight = 5
        wrapper.marginTop = 5
        wrapper.marginBottom = 5

        PartialView view = new PartialView("/test/test",[])



        wkhtmltoxService.makePdf(wrapper,view)

    }
}
