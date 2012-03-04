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

import java.util.HashMap;
import java.util.Map;

/**
 * Implements {@link ITag}.
 * 
 * @author marta
 * 
 */
abstract class AbstractTag implements ITag {

    public final static String NAME_ATTR_NAME = "name";

    public final static String ID_ATTR_NAME = "id";

    private String name;

    private Map<String, String> attrs = new HashMap<String, String>();

    public AbstractTag() {
    }

    public AbstractTag(String name) {
	this.name = name;

    }

    public AbstractTag(String name, Map<String, String> attrs) {
	if (attrs == null) {
	    throw new IllegalArgumentException(
		    "Attributes argument can not be null");
	}

	this.name = name;
	this.attrs = attrs;
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
    public Map<String, String> getAttributes() {
	return new HashMap<String, String>(this.attrs);
    }

    @Override
    public void setAttributes(Map<String, String> attrs) {
	if (attrs == null) {
	    throw new IllegalArgumentException("Argument can not be null");
	}

	this.attrs = new HashMap<String, String>(attrs);
    }

    @Override
    public void addAttribute(String name, String value) {
	this.attrs.put(name, value);
    }

    public String removeAttribute(String name) {
	return this.attrs.remove(name);
    }

    @Override
    public String getAttribute(String name) {
	return this.attrs.get(name);
    }

}
