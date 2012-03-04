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
 * This interface represents form element.
 * 
 * @author marta
 * 
 */
public interface IFormElement extends ITag {

    /**
     * Set element label.
     * @param label
     */
    void setLabel(String label);

    /**
     * Get element label.
     * @return
     */
    String getLabel();

    /**
     * Get additional data for this element, for example select tag: value - label pair.
     * 
     * @return
     */
    Map<String, String> getAdditionalData();

    /**
     * Set additional data for this element.
     * @param additionalData
     */
    void setAdditionalData(Map<String, String> additionalData);

}
