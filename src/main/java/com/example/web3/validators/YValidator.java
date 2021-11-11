package com.example.web3.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("yValidator")
public class YValidator implements Validator<Double> {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double aDouble) throws ValidatorException {
        if (aDouble <= -3 || aDouble >= 3) {
            FacesMessage msg = new FacesMessage("Y must be in range (-3; 3)");
            throw new ValidatorException(msg);
        }
    }

    public boolean validate(double aDouble){
        return aDouble > -3 && aDouble < 3;
    }
}
