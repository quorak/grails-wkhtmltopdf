package org.wkhtmltox

/**
 * @author tobiasnendel
 */
class WkhtmltoxException extends Exception {

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
