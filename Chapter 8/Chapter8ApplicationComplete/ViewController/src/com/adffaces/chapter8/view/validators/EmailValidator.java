
package com.adffaces.chapter8.view.validators;

import java.util.Collection;
import java.util.Collections;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.myfaces.trinidad.validator.ClientValidator;
public class EmailValidator implements Validator, ClientValidator {
    public EmailValidator() {
        super();
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String firstName = uIComponent.getAttributes().get("firstName").toString().toUpperCase();
        if (!object.toString().toUpperCase().startsWith(firstName.charAt(0) + "")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error",
                                                          "Email address should start with First Letter of the First Name"));
        }
    }

    @Override
    public String getClientLibrarySource(FacesContext facesContext) {
        return facesContext.getExternalContext().getRequestContextPath() + "/resources/js/emailValidator.js";
    }

    @Override
    public Collection<String> getClientImportNames() {
        return Collections.emptySet();
    }

    @Override
    public String getClientScript(FacesContext facesContext, UIComponent uIComponent) {
        return null;
    }

    @Override
    public String getClientValidation(FacesContext facesContext, UIComponent uIComponent) {
        String firstNameField = uIComponent.getAttributes().get("firstNameField").toString();
        return "new EmailValidator('"+firstNameField+"')";
    }
}
