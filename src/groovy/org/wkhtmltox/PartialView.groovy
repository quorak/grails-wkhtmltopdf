package org.wkhtmltox

/**
 * Created by IntelliJ IDEA.
 * User: tobiasnendel
 * Date: 18.04.11
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
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
