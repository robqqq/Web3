package com.example.web3.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.HashSet;
import java.util.Set;

@FacesValidator("rValidator")
public class RValidator implements Validator<Double> {
    private final Set<Double> rRange;

    public RValidator(){
        rRange = new HashSet<>();
        rRange.add(1.0);
        rRange.add(2.0);
        rRange.add(3.0);
        rRange.add(4.0);
        rRange.add(5.0);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double aDouble) throws ValidatorException {
        if (!rRange.contains(aDouble)){
            FacesMessage msg = new FacesMessage("R value must be in {1, 2, 3, 4, 5}");
            throw new ValidatorException(msg);
        }
    }

    public boolean validate(double aDouble){
        return rRange.contains(aDouble);
    }
}
