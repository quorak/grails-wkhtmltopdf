package org.wkhtmltox


/**
 * @author tobiasnendel
 */
class WkhtmltoxWrapper {



    // see http://madalgo.au.dk/~jakobt/wkhtmltoxdoc/wkhtmltopdf_0.10.0_rc2-doc.html#Outline Options


    //
    //Global Options
    //
    boolean collate								//						//Collate when printing multiple copies (default)
    boolean noCollate							//						//Do not collate when printing multiple copies
    //cookie-jar								//<path>				//Read and write cookies from and to the supplied cookie jar file
    Integer copies								//<number>				//Number of copies to print into the pdf file (default 1)
    Integer dpi									//<dpi>					//Change the dpi explicitly (this has no effect on X11 based systems)
    //extended-help								//						//Display more extensive help, detailing less common command switches
    boolean grayscale							//						//PDF will be generated in grayscale
    //help										//						//Display help
    //htmldoc									//						//Output program html help
    Integer  imageDpi							//<integer>				//When embedding images scale them down to this dpi (default 600)
    Integer  imageQuality					    //<integer>				//When jpeg compressing images use this quality (default 94)
    boolean lowquality							//						//Generates lower quality pdf/ps. Useful to shrink the result document space
    //manpage									//Output program man page
    Integer  marginBottom						//<unitreal>			//Set the page bottom margin (default 10mm)
    Integer  marginLeft							//<unitreal>			//Set the page left margin (default 10mm)
    Integer  marginRight						´//<unitreal>			//Set the page right margin (default 10mm)
    Integer  marginTop							//<unitreal>			//Set the page top margin (default 10mm)
    String orientation							//<orientation>			//Set orientation to Landscape or Portrait (default Portrait)
    String outputFormat							//<format>				//Specify an output format to use pdf or ps, instead of looking at the extention of the output filename
    Integer  pageHeight							//<unitreal>			//Page height
    String pageSize								//<Size>				//Set paper size to: A4, Letter, etc. (default A4)
    Integer  pageWidth							//<unitreal>			//Page width
    boolean noPdfCompression					//						//Do not use lossless compression on pdf objects
    //quiet,									//						//Be less verbose
    //read-args-from-stdin						//						//Read command line arguments from stdin
    //readme									//						//Output program readme
    String title								//<text>				//The title of the generated pdf file (The title of the first document is used if not specified)
    boolean useXserver							//						//Use the X server (some plugins and other stuff might not work without X11)
    //version,									//						//Output version information an exit

    //
    // Outline
    //

    //dump-default-toc-xsl*		                //                      //Dump the default TOC xsl style sheet to stdout
    //dump-outline*	    	                    //<file>                //Dump the outline to a file
    boolean outline                             //		                //Put an outline into the pdf (default)
    boolean noOutline		                    //                      //Do not put an outline into the pdf
    Integer  outlineDepth                      	//<level>	            //Set the depth of the outline (default 4)

    //
    //Page Options
    //

    //allow										//<path>				//Allow the file or files from the specified folder to be loaded (repeatable)
    boolean background							//						//Do print background (default)
    boolean noBackground						//						//Do not print background
    String checkboxCheckedSvg					//<path>				//Use this SVG file when rendering checked checkboxes
    String checkboxSvg							//<path>				//Use this SVG file when rendering unchecked checkboxes
    //cookie									//<name> <value>		//Set an additional cookie (repeatable)
    //custom-header								//<name> <value>		//Set an additional HTTP header (repeatable)
    //custom-header-propagation					//						//Add HTTP headers specified by --custom-header for each resource request.
    //no-custom-header-propagation				//						//Do not add HTTP headers specified by --custom-header for each resource request.
    boolean debugJavascript						//						//Show javascript debugging output
    boolean noDebugJavascript					//						//Do not show javascript debugging output (default)
    boolean defaultHeader						//						//Add a default header, with the name of the page to the left, and the page number to the right, this is short for: --header-left='[webpage]' --header-right='[page]/[toPage]' --top 2cm --header-line
    String encoding								//<encoding>			//Set the default text encoding, for input
    boolean disableExternalLinks				//						//Do not make links to remote web pages
    boolean enableExternalLinks					//						//Make links to remote web pages (default)
    boolean disableForms						//						//Do not turn HTML form fields into pdf form fields (default)
    boolean enableForms							//						//Turn HTML form fields into pdf form fields
    boolean images								//						//Do load or print images (default)
    boolean noImages							//						//Do not load or print images
    boolean disableInternalLinks				//						//Do not make local links
    boolean enableInternalLinks					//						//Make local links (default)
    boolean disableJavascript					//						//Do not allow web pages to run javascript
    boolean enableJavascript					//						//Do allow web pages to run javascript (default)
    Integer  javascriptDelay					//<msec>				//Wait some milliseconds for javascript finish (default 200)
    String loadErrorHandling					//<handler>				//Specify how to handle pages that fail to load: abort, ignore or skip (default abort)
    boolean disableLocalFileAccess				//						//Do not allowed conversion of a local file to read in other local files, unless explecitily allowed with --allow
    boolean enableLocalFileAccess				//						//Allowed conversion of a local file to read in other local files. (default)
    Integer  minimumFontSize					//<int>					//Minimum font size
    boolean excludeFromOutline					//						//Do not include the page in the table of contents and outlines
    boolean includeInOutline					//						//Include the page in the table of contents and outlines (default)
    Integer  pageOffset							//<offset>				//Set the starting page number (default 0)
    String password								//<password>			//HTTP Authentication password
    boolean disablePlugins						//						//Disable installed plugins (default)
    boolean enablePlugins						//						//Enable installed plugins (plugins will likely not work)
    //post										//<name> <value>		//Add an additional post field (repeatable)
    //post-file									//<name> <path>			//Post an additional file (repeatable)
    boolean printMediaType						//						//Use print media-type instead of screen
    boolean noPrintMediaType					//						//Do not use print media-type instead of screen (default)
    String proxy								//<proxy>				//Use a proxy
    //radiobutton-checked-svg					//<path>				//Use this SVG file when rendering checked radiobuttons
    //radiobutton-svg							//<path>				//Use this SVG file when rendering unchecked radiobuttons
    //run-script								//<js>					//Run this additional javascript after the page is done loading (repeatable)
    boolean disableSmartShrinking				//						//Disable the intelligent shrinking strategy used by WebKit that makes the pixel/dpi ratio none constant
    boolean enableSmartShrinking				//						//Enable the intelligent shrinking strategy used by WebKit that makes the pixel/dpi ratio none constant (default)
    boolean stopSlowScripts						//						//Stop slow running javascripts (default)
    boolean noStopSlowScripts					//						//Do not Stop slow running javascripts (default)
    boolean disableTocBackLinks					//						//Do not link from section header to toc (default)
    boolean enableTocBackLinks					//						//Link from section header to toc
    String userStyleSheet						//<url>					//Specify a user style sheet, to load with every page
    String username								//<username>			//HTTP Authentication username
    String windowStatus							//<windowStatus>		//Wait until window.status is equal to this string before rendering page
    float zoom									//<float>				//Use this zoom factor (default 1)

    //
    //Headers And Footer Options
    //

    String footerCenter							//<text>				//Centered footer text
    String footerFontName						//<name>				//Set footer font name (default Arial)
    Integer  footerFontSize						//<size>				//Set footer font size (default 12)
    String footerHtml							//<url>					//Adds a html footer
    String footerLeft							//<text>				//Left aligned footer text
    boolean footerLine							//						//Display line above the footer
    boolean noFooterLine						//						//Do not display line above the footer (default)
    String footerRight							//<text>				//Right aligned footer text
    Integer  footerSpacing						//<real>				//Spacing between footer and content in mm (default 0)
    String headerCenter							//<text>				//Centered header text
    String headerFontName						//<name>				//Set header font name (default Arial)
    String headerFontSize						//<size>				//Set header font size (default 12)
    String headerHtml							//<url>					//Adds a html header
    String headerLeft							//<text>				//Left aligned header text
    boolean headerLine							//						//Display line below the header
    boolean noHeaderLine						//						//Do not display line below the header (default)
    String headerRight							//<text>				//Right aligned header text
    Integer  headerSpacing						//<real>				//Spacing between header and content in mm (default 0)

    //replace*									//<name> <value>		//Replace [name] with value in header and footer (repeatable)

    //
    //TOC Options
    //

    boolean disableDottedLines					//						//Do not use dottet lines in the toc
    String tocHeaderText						//<text>				//The header text of the toc (default Table of Content)
    Integer  tocLevelIndentation				//<width>				//For each level of headings in the toc indent by this length (default 1em)
    boolean disableTocLinks						//						//Do not link from toc to sections
    float tocTextSizeShrink						//<real>				//For each level of headings in the toc the font is scaled by this facter (default 0.8)
    String xslStyleSheet						//<file>				//Use the supplied xsl style sheet for printing the table of content




    ArrayList toArgumentsList(){
        List<String> arguments = new ArrayList<String>()
        metaClass.properties.each{ MetaProperty property ->

            def propertyValue = getProperty(property.name)

            if(propertyValue != null){
                def argumentName = property.name.replaceAll(/(\B[A-Z])/,'-$1').toLowerCase()
                switch(property.type){
                case Boolean:
                case boolean:
                    if(propertyValue){
                        arguments.add("--" + argumentName)
                    }
                    break
                case String:
                    arguments.add("--" + argumentName)
                    arguments.add("" + propertyValue + "")
                    break
                case Integer:
                case int:
                    arguments.add("--" + argumentName)
                    arguments.add(propertyValue.toString())
                    break
                case URL:
                    break
                default:
                    break
                }
            }
        }
        return arguments
    }

    String toArgumentsString(){
        return toArgumentsList().join(" ")
    }






}
