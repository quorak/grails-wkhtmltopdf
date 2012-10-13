package org.wkhtmltox

/**
 * @author tobiasnendel
 */
class PartialView {

    PartialView(String viewName, model = [], String pluginName = null) {
        this.viewName = viewName
        this.model = model
        this.pluginName = pluginName
    }

    String viewName
    def model
    String pluginName
}
