package com.ticCore.validators;

import com.ticCore.beans.Player;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**Class to validate inputs for player password reset
 * Created by student on 9/30/16.
 */
@Service
public class PassResetValidator implements  Validator {

    public boolean supports(Class<?> klass) {
        return Player.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {        Player player = (Player) target;

        try {
            InternetAddress emailAddr = new InternetAddress(player.getEmail());
            emailAddr.validate();
        } catch (AddressException ex) {
            errors.rejectValue("email", "email.notValid");
        }

        ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
        if (!(player.getPassword().equals(player.getPassword1()))) {
            errors.rejectValue("password", "password.mustMatch");
        }


    }
}


