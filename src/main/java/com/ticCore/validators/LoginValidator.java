package com.ticCore.validators;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**Simple validator for login credentials
 * Created by student on 9/19/16.
 */
public class LoginValidator implements RawValidator {

    public <T> Map<String,String> validate(T subject) {
        HttpServletRequest request = (HttpServletRequest) subject;
        Map<String,String> errors = new HashMap<String, String>();
        if ((String) request.getParameter("email") == null)
            errors.put("email","Invalid Email");
        if ((String) request.getParameter("password") == null)
            errors.put("password","Password Invalid or not supplied");
        return errors.size()>0? errors:null;
    }


}
