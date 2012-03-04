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

import java.util.List;

/**
 * Representation of "collection" of form elements.
 * 
 * @author marta
 * 
 */
public interface IFormComponent {

    /**
     * Get position of this form component.
     * @return
     */
    int getOrder();

    /**
     * Set position of this form component.
     * @param order
     */
    void setOrder(int order);

    /**
     * Get header of this form component.
     * @return
     */
    String getHeader();

    /**
     * Set header of this form component.
     * 
     * @param header
     */
    void setHeader(String header);

    /**
     * Add form element.
     * 
     * @param element
     */
    void addElement(IFormElement element);

    /**
     * Remove form element.
     * 
     * @param element
     */
    void removeElement(IFormElement element);

    /**
     * Remove element by name.
     * 
     * @param elementName
     * @return
     */
    boolean removeElementByName(String elementName);

    /**
     * Get all form elements.
     * 
     * @return
     */
    List<IFormElement> getElements();

    /**
     * Set all form elements.
     * 
     * @param elements
     */
    void setElements(List<IFormElement> elements);

}
