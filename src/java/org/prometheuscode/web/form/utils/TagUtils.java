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
package org.prometheuscode.web.form.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Utility helpers class for creating tags.
 * 
 * @author marta
 * 
 */
public class TagUtils {

    /**
     * Create open tag with only one attribute.
     * 
     * @param tagName
     * @param tagAttr
     * @param tagAttrValue
     * @return
     */
    public static String createSimpleOpenTag(String tagName, String tagAttr,
	    String tagAttrValue) {
	return String.format("<%1$s %2$s=\"%3$s\">", tagName, tagAttr,
		tagAttrValue);
    }

    /**
     * Create tag with only one attribute and body.
     * 
     * @param tagName
     * @param tagAttr
     * @param tagAttrValue
     * @return
     */
    public static String createSimpleTag(String tagName, String tagAttr,
	    String tagAttrValue, String tagContent) {
	return String.format("<%1$s %2$s=\"%3$s\">%4$s</%1$s>", tagName, tagAttr,
		tagAttrValue, tagContent);
    }

    /**
     * Create open tag using map as attributes source.
     * 
     * @param tagName
     * @param attrs
     * @return
     */
    public static String createStartTag(String tagName,
	    Map<String, String> attrs) {
	return TagUtils.createOpenTag(tagName, attrs);
    }

    /**
     * Create tag with body.
     * 
     * @param tagName
     * @param attrs
     * @param content
     * @return
     */
    public static String createTag(String tagName, Map<String, String> attrs,
	    String content) {
	StringBuilder tag = new StringBuilder();
	tag.append(TagUtils.createOpenTag(tagName, attrs));
	tag.append(content);

	tag.append("</" + tagName + ">");

	return tag.toString();
    }

    /**
     * Create end tag.
     * 
     * @param tagName
     * @return
     */
    public static String createEndTag(String tagName) {
	return ("</" + tagName + ">");
    }

    /*
     * Create the tag without the body.
     */
    private static String createOpenTag(String tagName,
	    Map<String, String> attrs) {
	StringBuilder tag = new StringBuilder();
	tag.append("<" + tagName);

	if (attrs != null) {
	    Set<Entry<String, String>> entries = attrs.entrySet();
	    for (Entry<String, String> entry : entries) {
		String key = entry.getKey();
		String value = entry.getValue();
		tag.append(" " + key + "=" + "\"" + value + "\"");
	    }
	}

	tag.append(">");
	return tag.toString();
    }

}
