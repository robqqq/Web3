package com.example.web3.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("xValidator")
public class XValidator implements Validator<Double> {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double o) throws ValidatorException {
        if (o <= -5 || o >= 5){
            FacesMessage msg = new FacesMessage("X must be in range (-5; 5)");
            throw new ValidatorException(msg);
        }
    }

    public boolean validate(double o){
        return o > -5 && o < 5;
    }
}
