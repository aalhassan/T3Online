package com.TicTest;

import com.ticCore.controllers.AccountServices;
import com.ticCore.controllers.AccountServices;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by student on 9/10/16.
 */

public class AccountServicesTest {
    AccountServices accountServices;
    @Before
    public void setup () {
        accountServices = new AccountServices();
    }

    @Test
    public void loginSuccessful() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI("/login");
        request.setAttribute("email", "realmej@hotmail.com");
        request.setAttribute("password", "firstPass");
        MockHttpServletResponse response = new MockHttpServletResponse();

        accountServices.login(request,response);

        assertEquals("Session LoggedIn attribute Failed ", "successful", session.getAttribute("loggedIn"));
        assertEquals("Username value failed","realmej@hotmail.com", session.getAttribute("logged_in_email"));
        assertNull("RequestErrors is to be empty/null",  (ArrayList<String>) request.getAttribute("errors"));

    }

   @Test
    public void loginFailedIfEmailMissing() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI("/login");
        request.setAttribute("email", null);
        request.setAttribute("password", "firstPass");
        MockHttpServletResponse response = new MockHttpServletResponse();

        accountServices.login(request,response);

        assertNull("Session LoggedIn attribute Failed ",  session.getAttribute("loggedIn"));
        assertTrue("Errors should contain email",  ((ArrayList<String>) request.getAttribute("errors")).contains("email"));

    }
/*
    @Test
    public void loginPasswordFailed() {
        AccountServices accountServices = new AccountServices();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setRequestURI("/login");
        request.setAttribute("email", "mejbo@davisstudies.com");
        request.setAttribute("password", "Passing");
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertNotEquals("Session Logged In Value Failed ", "successful", session.getAttribute("loggedIn"));
        assertNotNull("Error Arrays Failed", session.getAttribute("errors"));
        assertTrue("Errors reason failure", ((ArrayList<String>) session.getAttribute("error")).contains("password"));

    }*/
}
