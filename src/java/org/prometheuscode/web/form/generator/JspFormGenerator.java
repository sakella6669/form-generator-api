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
import java.util.List;
import java.util.Map;

import org.prometheuscode.web.form.FormElement;
import org.prometheuscode.web.form.IForm;
import org.prometheuscode.web.form.IFormComponent;
import org.prometheuscode.web.form.IFormElement;
import org.prometheuscode.web.form.utils.TagUtils;

/**
 * JSP aware form generator.
 * 
 * It assumes that user is going to bind form variables and errors by using a
 * map (Errors map and Values Map). The name for these maps can be choosen by
 * passing appropriate configuration.
 * 
 * @author marta
 */
public class JspFormGenerator implements IFormGenerator {

    private FormConfig formConfig;

    public JspFormGenerator() {
    }

    /**
     * Set form config.
     * 
     * @return
     */
    public FormConfig getFormConfig() {
	return this.formConfig;
    }

    /**
     * Get form config.
     * 
     * @param config
     */
    public void setFormConfig(FormConfig config) {
	this.formConfig = config;
    }

    @Override
    public String generateForm(IForm form) {
	if (form == null) {
	    throw new IllegalArgumentException("Form can not be null");
	}
	StringBuilder result = new StringBuilder();

	result.append(TagUtils.createStartTag("form", form.getAttributes()));
	result.append("\n"); /* to look readable */

	this.createFormComponents(form, result);

	result.append("\n");
	result.append(TagUtils.createEndTag("form"));

	return result.toString();
    }

    /*
     * Create form components.
     */
    private void createFormComponents(IForm form, StringBuilder buf) {
	List<IFormComponent> components = form.getFormComponents();

	for (IFormComponent comp : components) {
	    buf.append(TagUtils.createSimpleOpenTag("div", "class",
		    this.formConfig.getAttribute(FormConfig.ATTR_COMPONENT_CLASS)));
	    buf.append("\n");

	    /*
	     * header can be a question for example.
	     */
	    String header = comp.getHeader();
	    buf.append(TagUtils.createSimpleTag("div", "class",
		    this.formConfig.getAttribute(FormConfig.ATTR_HEADER_CLASS),
		    header));

	    this.createComponentElements(comp, buf);

	    buf.append(TagUtils.createEndTag("div"));
	}
    }

    /*
     * Create component elements.
     */
    private void createComponentElements(IFormComponent component,
	    StringBuilder buf) {
	List<IFormElement> elements = component.getElements();
	buf.append("\n"); /* to look readable */
	for (IFormElement elem : elements) {
	    buf.append(TagUtils
		    .createSimpleOpenTag("div", "class", this.formConfig
			    .getAttribute(FormConfig.ATTR_ELEMENT_CLASS)));

	    this.createTag(elem, buf);

	    buf.append("\n"); /* to look readable */
	    buf.append(TagUtils.createEndTag("div"));
	    buf.append("\n");
	}
    }

    /*
     * Helper function to a create tag.
     */
    private void createTag(IFormElement element, StringBuilder buf) {

	String elemName = element.getName();
	if (elemName.equals("input")) {
	    this.createInputTag(element, buf);
	} else if (elemName.equals("select")) {
	    this.createSelectTag(element, buf);

	} else if (elemName.equals("textarea")) {
	    this.createTextareaTag(element, buf);
	}

    }

    /*
     * Create input element of form.
     */
    private void createInputTag(IFormElement element, StringBuilder buf) {
	String label = element.getLabel();
	String name = element.getName();
	String elemAttrName = element.getAttribute(FormElement.NAME_ATTR_NAME);
	String elemAttrType = element.getAttribute(FormElement.TYPE_ATTR_NAME);

	Map<String, String> attrs = element.getAttributes();
	String vName = this.formConfig
		.getAttribute(FormConfig.ATTR_VALUES_MAP_VAR_NAME);
	String valueVar = this.getElVar(vName, elemAttrName);

	if (!(elemAttrType.equals("radio"))
		&& !(elemAttrType.equals("checkbox"))) {
	    buf.append(TagUtils.createTag("label", null, label) + "\n");

	    /* file can not have a value */
	    if (!elemAttrType.equals("file")) {
		attrs.put(FormElement.VALUE_ATTR_NAME, valueVar);
	    }

	    buf.append(TagUtils.createTag(name, attrs, "") + "\n");

	    String eName = this.formConfig
		    .getAttribute(FormConfig.ATTR_ERRORS_MAP_VAR_NAME);
	    String errorVar = this.getElVar(eName, elemAttrName);

	    buf.append(TagUtils.createSimpleTag("div", "class",
		    this.formConfig.getAttribute(FormConfig.ATTR_ERROR_CLASS),
		    errorVar));
	} else { /* create checkbox or radio button */
	    buf.append(TagUtils.createTag("label", null, label));
	    buf.append(TagUtils.createStartTag("div", null));
	    
	    Map<String, String> optionsData = element.getAdditionalData();
	    for (String value : optionsData.keySet()) {

		attrs.put(FormElement.VALUE_ATTR_NAME, value);
		String startTag = TagUtils.createStartTag(name, attrs);
		/* inject El conditional for "checked" */
		buf.append(this.injectConditionalSelection(value, elemAttrName,
			startTag, "checked"));

		buf.append(TagUtils.createTag("span", null,
			optionsData.get(value))
			+ "\n");

		buf.append(TagUtils.createEndTag(name));
		/*
		 * it will not parse as xml if not <br></br>
		 */
		buf.append("<br></br>");
	    }

	    buf.append(TagUtils.createEndTag("div"));
	}

    }

    /*
     * Create text area.
     */
    private void createTextareaTag(IFormElement element, StringBuilder buf) {
	String elemName = element.getName();
	String label = element.getLabel();
	String elemNameAttr = element.getAttribute(FormElement.NAME_ATTR_NAME);
	
	buf.append(TagUtils.createTag("label", null, label));
	
	String elVar = this.getElVar(this.formConfig
		.getAttribute(FormConfig.ATTR_VALUES_MAP_VAR_NAME),
		elemNameAttr);
	buf.append(TagUtils.createTag(elemName, element.getAttributes(), elVar));

	String errorVar = this.getElVar(this.formConfig
		.getAttribute(FormConfig.ATTR_ERRORS_MAP_VAR_NAME),
		elemNameAttr);
	buf.append(TagUtils.createSimpleTag("div", "class",
		this.formConfig.getAttribute(FormConfig.ATTR_ERROR_CLASS),
		errorVar));
    }

    /*
     * Create select tag.
     */
    private void createSelectTag(IFormElement element, StringBuilder buf) {
	String elemName = element.getName();
	String elemlabel = element.getLabel();
	String elemAttrName = element.getAttribute(FormElement.NAME_ATTR_NAME);
	Map<String, String> options = element.getAdditionalData();

	Map<String, String> attrs = new HashMap<String, String>();
	StringBuilder optionsBuf = new StringBuilder();
	for (String value : options.keySet()) {
	    optionsBuf.append("\n");
	    String label = options.get(value);

	    attrs.put(FormElement.VALUE_ATTR_NAME, value);
	    String openOptionTag = TagUtils.createStartTag("option", attrs);

	    optionsBuf.append(this.injectConditionalSelection(value,
		    elemAttrName, openOptionTag, "selected"));
	    optionsBuf.append(TagUtils.createTag("span", null, label));

	    optionsBuf.append(TagUtils.createEndTag("option"));
	}

	buf.append(TagUtils.createTag("label", null, elemlabel));
	buf.append(TagUtils.createTag(elemName, element.getAttributes(),
		optionsBuf.toString()));
    }

    /*
     * Get El expression variable.
     */
    private String getElVar(String valueVar, String elemName) {
	return String.format("${%1$s['%2$s']}", valueVar, elemName);
    }

    /*
     * Put conditional selection into the tag(select, radio, checkbox).
     */
    private String injectConditionalSelection(String valueToCompare,
	    String mapVarName, String tag, String selectionKeyWord) {
	String valueMap = this.formConfig
		.getAttribute(FormConfig.ATTR_VALUES_MAP_VAR_NAME)
		+ "[\""
		+ mapVarName + "\"]";

	String openTagPart = tag.substring(0, tag.lastIndexOf(">"));

	String conditionalSelection = String.format(
		" ${(%2$s.contains('%1$s')) ? \"%3$s\" : ''}", valueToCompare,
		valueMap, selectionKeyWord);
	openTagPart = openTagPart.concat(conditionalSelection);
	return (openTagPart + ">");
    }

}
