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

import java.util.Map;

/**
 * Implementation of {@link IFormElement}.
 * 
 * @author marta
 * 
 */
public class FormElement extends AbstractTag implements IFormElement {

    public final static String NAME_ATTR_NAME = "name";

    public final static String TYPE_ATTR_NAME = "type";

    public final static String VALUE_ATTR_NAME = "value";

    private String name;

    private String label;

    private Map<String, String> additionalData;

    public FormElement() {
    }

    @Override
    public String getName() {
	return this.name;
    }

    @Override
    public void setName(String name) {
	this.name = name;
    }

    @Override
    public void setLabel(String label) {
	this.label = label;
    }

    @Override
    public String getLabel() {
	return this.label;
    }

    @Override
    public Map<String, String> getAdditionalData() {
	return this.additionalData;
    }

    @Override
    public void setAdditionalData(Map<String, String> additionalData) {
	this.additionalData = additionalData;

    }

}
