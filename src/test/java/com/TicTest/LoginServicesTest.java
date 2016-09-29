package com.TicTest;

import com.ticCore.controllers.LoginServices;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by student on 9/10/16.
 */

public class LoginServicesTest {
    LoginServices loginServices;
    private static final String LOGIN_PATH = "/login";
    @Before
    public void setup () {
        loginServices = new LoginServices();
    }

    @Test
    public void loginSuccessful() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setAttribute("email", "realmej@hotmail.com");
        request.setAttribute("password", "firstPass");


        loginServices.login(request);

        assertEquals("Session LoggedIn attribute Failed ", "Successful", session.getAttribute("loggedIn"));
        assertEquals("Username value failed","realmej@hotmail.com", session.getAttribute("logged_in_email"));
        assertNull("RequestErrors is to be null",  (HashMap<String,String>) request.getAttribute("errors"));

    }

   @Test
    public void loginFailedIfEmailMissing() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setAttribute("email", null);
        request.setAttribute("password", "first");
        MockHttpServletResponse response = new MockHttpServletResponse();

        loginServices.login(request);

        assertNull("Session LoggedIn attribute Failed ",  session.getAttribute("loggedIn"));
        assertTrue("Errors should contain email",  ((HashMap<String,String>) request.getAttribute("errors")).containsKey("email"));

    }

    @Test
    public void loginFailedIfPasswordMissing() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setAttribute("email", "realmej@hotmail.com");
        request.setAttribute("password", null);


        loginServices.login(request);

        assertNull("Session LoggedIn attribute Failed ",  session.getAttribute("loggedIn"));
        assertTrue("Errors should contain password",  ((HashMap<String,String>) request.getAttribute("errors")).containsKey("password"));
    }

    @Test
    public void loginAuthFailed() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setAttribute("email", "realmej@hotmail.com");
        request.setAttribute("password", "wrongPass");
        MockHttpServletResponse response = new MockHttpServletResponse();

        loginServices.login(request);

        assertNull("Session LoggedIn attribute Failed ",  session.getAttribute("loggedIn"));
        assertTrue("Errors should contain auth",  ((HashMap<String,String>) request.getAttribute("errors")).containsKey("auth"));
    }
}
