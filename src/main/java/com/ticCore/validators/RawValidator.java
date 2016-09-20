package com.ticCore.validators;

import java.util.List;

/**
 * Created by student on 9/19/16.
 */
public interface RawValidator {
    /**Validates an object
     * @param subject object to validate
     */

    public <T> void validate(T subject);

    /**Gets results of validation
     * @return List of all errors found
     */
    public  List<?> getValidationResults();
}
