package com.ticCore.validators;

import java.util.List;
import java.util.Map;

/**
 * Created by student on 9/19/16.
 */
public interface RawValidator {
    /**Validates an object
     * @param subject object to validate
     * @return List of all errors found mapped to error messages, or null if none
     */

    public <T> Map<?,String>  validate(T subject);

}
