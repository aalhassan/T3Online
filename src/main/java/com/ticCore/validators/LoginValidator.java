package com.ticCore.validators;

import javax.servlet.http.HttpServletRequest;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 9/19/16.
 */
public class LoginValidator implements RawValidator {
    private ArrayList<String> errors;

    public <T> void validate(T subject) {
        HttpServletRequest request = (HttpServletRequest) subject;
        errors = new ArrayList<String>();
        if ((String) request.getAttribute("email") == null)
            errors.add("email");
        if ((String) request.getAttribute("password") == null)
            errors.add("password");
    }

    public List<String> getValidationResults() {
        return errors;
    }
}
