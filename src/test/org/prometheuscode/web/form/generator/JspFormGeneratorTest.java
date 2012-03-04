package org.prometheuscode.web.form.generator;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.prometheuscode.web.form.Form;
import org.prometheuscode.web.form.FormComponent;
import org.prometheuscode.web.form.FormElement;
import org.prometheuscode.web.form.IForm;
import org.prometheuscode.web.form.IFormComponent;
import org.prometheuscode.web.form.IFormElement;

public class JspFormGeneratorTest {

    private IForm testForm;

    @Before
    public void setUp() {
	IForm form = new Form();

	IFormComponent comp = new FormComponent();
	form.addComponent(comp);

	comp.setHeader("Question?");

	IFormElement elem = new FormElement();
	comp.addElement(elem);
	elem.setLabel("label");
	elem.setName("input");

	elem.addAttribute("type", "text");
	elem.addAttribute("name", "input_name");

	IFormElement elemTextArea = new FormElement();
	comp.addElement(elemTextArea);
	elemTextArea.setLabel("label");
	elemTextArea.setName("textarea");

	elemTextArea.addAttribute("rows", "10");
	elemTextArea.addAttribute("cols", "10");
	elemTextArea.addAttribute("name", "text_area");

	IFormElement elemSelect = new FormElement();
	comp.addElement(elemSelect);
	elemSelect.setLabel("label");
	elemSelect.setName("select");
	elemSelect.addAttribute("name", "select");

	Map<String, String> options = new HashMap<String, String>();
	elemSelect.setAdditionalData(options);
	options.put("zlo", "zlo");
	options.put("madness", "madness");
	options.put("hate", "hate");

	IFormElement elemRadio = new FormElement();
	elemRadio.setLabel("awesome");
	elemRadio.setName("input");

	elemRadio.addAttribute("name", "_radio_");
	elemRadio.addAttribute("type", "radio");

	Map<String, String> radioOptions = new LinkedHashMap<String, String>();
	radioOptions.put("awesome", "awesome");
	radioOptions.put("not_awesome", "not_awesome");

	elemRadio.setAdditionalData(radioOptions);

	comp.addElement(elemRadio);

	
	this.testForm = form;
    }

    @Test
    public void testGenerateForm() {
	FormConfig conf = new FormConfig();
        JspFormGenerator gen = new JspFormGenerator();
        gen.setFormConfig(conf);
        
        String result = gen.generateForm(this.testForm);

        /* this is needed to get xml document from generator output */
        String xmlComplainResult = result.replaceAll("\\$\\{.*?\\}", "");
        InputStream in = new ByteArrayInputStream(xmlComplainResult.getBytes());

        /*
         * prepare testing
         */
        DocumentBuilder builder = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch ( ParserConfigurationException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = builder.parse(in);
        } catch ( SAXException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
         * testing starts here
         */
        assertEquals("There should be only one! ...Form tag...", 1, doc.getChildNodes().getLength());

        Node el = doc.getFirstChild();

        assertEquals("first element should be form: ", "form", el.getNodeName());

        Node textArea = doc.getElementsByTagName("textarea").item(0);
        assertEquals("there should be text area element in a form: ", "textarea", textArea.getNodeName());
        assertEquals("textarea rows should be equal 10: ", "10", textArea.getAttributes().getNamedItem("rows")
                                                                         .getNodeValue());

        Node select = doc.getElementsByTagName("select").item(0);
        assertEquals("there should be select element in a form: ", "select", select.getNodeName());
        assertEquals("select should be named *select*: ", "select", select.getAttributes().getNamedItem("name")
                                                                          .getNodeValue());

        NodeList inputNodes = doc.getElementsByTagName("input");

        assertEquals("there should be 4 input tags(one text + 2 radio): ", 4, inputNodes.getLength());

        Node firstRadio = inputNodes.item(1);

        assertEquals("there should be radio element in a form: ", "input", firstRadio.getNodeName());
        assertEquals("radio should be named *_radio_*: ", "_radio_", firstRadio.getAttributes().getNamedItem("name")
                                                                               .getNodeValue());

        assertEquals("first radio should have value *awesome*: ", "awesome", firstRadio.getFirstChild()
                                                                                       .getTextContent());

        Node secondRadio = inputNodes.item(2);

        assertEquals("there should be radio element in a form: ", "input", secondRadio.getNodeName());
        assertEquals("radio should be named *_radio_*: ", "_radio_", secondRadio.getAttributes().getNamedItem("name")
                                                                                .getNodeValue());

        assertEquals("second radio should have value *not_awesome*: ", "not_awesome", secondRadio.getFirstChild().getTextContent());
    }
}
