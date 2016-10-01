package com.ticCore.validators;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.ticCore.beans.Player;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**Class to validate player registration
 * Created by student on 9/30/16.
 */
@Service
public class PlayerValidator implements  Validator {

    public boolean supports(Class<?> klass) {
        return Player.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {
        Player player = (Player) target;
        ValidationUtils.rejectIfEmpty(errors, "firstName",
                "firstName.required");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
        ValidationUtils.rejectIfEmpty(errors, "answer1", "answers.required");
        ValidationUtils.rejectIfEmpty(errors, "answer2", "answers.required");
        try {
            InternetAddress emailAddr = new InternetAddress(player.getEmail());
            emailAddr.validate();
        } catch (AddressException ex) {
            errors.rejectValue("email", "email.notValid");
        }
        if (!(player.getPassword().equals(player.getPassword1()))) {
            errors.rejectValue("password", "password.mustMatch");
        }


    }
}


