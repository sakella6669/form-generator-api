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
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of {@link IFormComponent}.
 * 
 * @author marta
 * 
 */
public class FormComponent implements IFormComponent {

    private int order;

    private String header;

    private List<IFormElement> elements = new ArrayList<IFormElement>();

    public FormComponent() {
    }

    @Override
    public int getOrder() {
	return this.order;
    }

    @Override
    public void setOrder(int order) {
	this.order = order;
    }

    @Override
    public String getHeader() {
	return this.header;
    }

    @Override
    public void setHeader(String header) {
	this.header = header;
    }

    @Override
    public void addElement(IFormElement element) {
	if (element == null) {
	    throw new IllegalArgumentException(
		    "Element being added can not be null");
	}

	this.elements.add(element);

    }

    @Override
    public void removeElement(IFormElement element) {
	if (element == null) {
	    throw new IllegalArgumentException(
		    "Element being added can not be null");
	}

	this.elements.remove(element);

    }

    @Override
    public boolean removeElementByName(String elementName) {
	if (elementName == null) {
	    throw new IllegalArgumentException(
		    "Element being added can not be null");
	}

	boolean removed = false;
	Iterator<IFormElement> iter = this.elements.iterator();
	IFormElement elem = null;
	while (iter.hasNext()) {
	    elem = iter.next();
	    String name = elem.getAttribute("name");
	    if (name.equals(elementName)) {
		iter.remove();
		removed = true;
		break;
	    }
	}

	return removed;

    }

    @Override
    public List<IFormElement> getElements() {
	return new ArrayList<IFormElement>(this.elements);
    }

    @Override
    public void setElements(List<IFormElement> elements) {

	if (elements == null) {
	    throw new IllegalArgumentException(
		    "List of form elements can not be null");
	}

	this.elements.clear();
	this.elements.addAll(elements);
    }

}
