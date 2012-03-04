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
 * Representation of form tag.
 * 
 * @author marta
 * 
 */
public interface ITag {

    /**
     * Get tag name.
     * 
     * @return
     */
    String getName();

    /**
     * Set tag name.
     * 
     * @param name
     */
    void setName(String name);

    /**
     * Get tag attribute.
     * 
     * @param name
     * @return
     */
    String getAttribute(String name);

    /**
     * Set tag attribute.
     * 
     * @param name
     * @param value
     */
    void addAttribute(String name, String value);

    /**
     * Remove tag attribute.
     * 
     * @param name
     * @return
     */
    String removeAttribute(String name);

    /**
     * Get all tag attributes.
     * 
     * @return
     */
    Map<String, String> getAttributes();

    /**
     * Set tag attributes.
     * 
     * @param attr
     */
    void setAttributes(Map<String, String> attr);

}
