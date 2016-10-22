package com.ticCore.validators;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by student on 10/17/16.
 */
public class LoginValidatorTest {
    private LoginValidator loginValidator;


    @Before
    public void setUp() throws Exception {
        loginValidator = new LoginValidator();



    }

    @Test
    public void testDataIsValid() throws Exception {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setParameter("email", "nas@mail.com");
        mockHttpServletRequest.setParameter("password", "testPass");
        Map<String,String>  errors = loginValidator.validate(mockHttpServletRequest);
        assertNull("There should be no errors", errors) ;
    }

    @Test
    public void testDataIsNotValid() throws Exception {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Map<String,String>  errors = loginValidator.validate(mockHttpServletRequest);
        assertTrue("There should be an email missing error", errors.containsKey("email"));
        assertTrue("There should be a password missing error", errors.containsKey("password"));
    }


}