package org.prometheuscode.web.form;

import java.util.List;

/**
 * Interface representing Form. It is made of "components" which defines
 * different parts of form.
 * 
 * 
 * @author marta
 */
public interface IForm extends ITag {

    /**
     * Add form component.
     * 
     * @param component
     */
    public void addComponent(IFormComponent component);

    /**
     * Remove form component.
     * 
     * @param component
     */
    void removeComponent(IFormComponent component);

    /**
     * Get all form componenets of this form.
     * 
     * @return
     */
    List<IFormComponent> getFormComponents();

    /**
     * Set or replace the form components of this form.
     * 
     * @param formElements
     */
    void setFormComponents(List<IFormComponent> formElements);

}
