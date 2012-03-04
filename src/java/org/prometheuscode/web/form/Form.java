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
package org.prometheuscode.web.form;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link IForm}.
 * 
 * @author marta
 */
public class Form extends AbstractTag implements IForm {

    public final static String ACTION_ATTR_NAME = "action";

    public final static String ENCTYPE_ATTR_NAME = "enctype";

    public final static String METHOD_ATTR_NAME = "method";

    private List<IFormComponent> formElements = new ArrayList<IFormComponent>();

    public Form() {
    }

    public Form(String name, Map<String, String> attr) {
	super(name, attr);

    }

    public Form(String name) {
	super(name);

    }

    @Override
    public void addComponent(IFormComponent element) {
	if (element == null) {
	    throw new IllegalArgumentException(
		    "Element being added can not be null");
	}

	this.formElements.add(element);
    }

    @Override
    public void removeComponent(IFormComponent element) {
	if (element == null) {
	    throw new IllegalArgumentException(
		    "Element being added can not be null");
	}

	this.formElements.remove(element);
    }

    @Override
    public List<IFormComponent> getFormComponents() {
	return new ArrayList<IFormComponent>(this.formElements);
    }

    @Override
    public void setFormComponents(List<IFormComponent> formElements) {

	if (formElements == null) {
	    throw new IllegalArgumentException(
		    "List of form elements can not be null");
	}

	this.formElements.clear();
	this.formElements.addAll(formElements);
    }

}
