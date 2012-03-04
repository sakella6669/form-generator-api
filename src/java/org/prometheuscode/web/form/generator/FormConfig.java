/*
 * Copyright (C) 2011 Marta Spodymek
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.prometheuscode.web.form.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for form generator.
 * 
 * <p>
 * It gathers all required presentation dependend attributes for form generator.
 * </p>
 * 
 * @author marta
 * 
 */
public class FormConfig {

    /*
     * Constants required for propper configuration of JspFormGenerator.
     */
    
    public final static String ATTR_COMPONENT_CLASS = "comp_class";

    public final static String ATTR_ELEMENT_CLASS = "elem_class";

    public final static String ATTR_HEADER_CLASS = "header_class";

    public final static String ATTR_ERROR_CLASS = "error_class";

    public final static String ATTR_VALUES_MAP_VAR_NAME = "var_name";

    public final static String ATTR_ERRORS_MAP_VAR_NAME = "error_name";

    public final static String ATTR_SUBMIT_LABEL = "submit_label";

    
    private String defaultComponentClassName = "_form_component";

    private String defaultElementClassName = "_form_component_element";

    private String defaultComponentHeaderClassName = "_form_component_header";

    private String defaultErrorClassName = "_error";

    private String defaultValuesMapVariable = "inputs";

    private String defaultErrorsMapVariable = "errors";

    private String defaultSubmitLabel = "submit";

    
    private Map<String, String> formConfiguration = new HashMap<String, String>();

    
    public FormConfig() {
	this.formConfiguration.put(ATTR_COMPONENT_CLASS,
		defaultComponentClassName);
	this.formConfiguration.put(ATTR_ELEMENT_CLASS, defaultElementClassName);
	this.formConfiguration.put(ATTR_HEADER_CLASS,
		defaultComponentHeaderClassName);
	this.formConfiguration.put(ATTR_ERROR_CLASS, defaultErrorClassName);
	this.formConfiguration.put(ATTR_VALUES_MAP_VAR_NAME,
		defaultValuesMapVariable);
	this.formConfiguration.put(ATTR_ERRORS_MAP_VAR_NAME,
		defaultErrorsMapVariable);
	this.formConfiguration.put(ATTR_SUBMIT_LABEL, this.defaultSubmitLabel);
    }

    /**
     * Set form config attribute.
     * 
     * @param attrName
     * @param attrValue
     */
    public void setAttrbiute(String attrName, String attrValue) {
	this.formConfiguration.put(attrName, attrValue);
    }

    /**
     * Get form config attribute.
     * 
     * @param attrName
     * @return
     */
    public String getAttribute(String attrName) {
	return this.formConfiguration.get(attrName);
    }

}
