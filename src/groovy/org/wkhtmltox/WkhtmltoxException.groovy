package org.wkhtmltox

/**
 * Created by IntelliJ IDEA.
 * User: tobiasnendel
 * Date: 21.04.11
 * Time: 09:07
 * To change this template use File | Settings | File Templates.
 */
class WkhtmltoxException extends  Exception{

    WkhtmltoxException() {
    }

    WkhtmltoxException(String s) {
        super(s)
    }

    WkhtmltoxException(String s, Throwable throwable) {
        super(s, throwable)
    }

    WkhtmltoxException(Throwable throwable) {
        super(throwable)
    }
}
