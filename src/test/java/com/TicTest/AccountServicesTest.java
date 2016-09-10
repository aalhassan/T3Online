package com.TicTest;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.ticCore.controllers.AccountServices;
import com.ticCore.controllers.AccountServices;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by student on 9/10/16.
 */

public class AccountServicesTest {
    @Before
    public void setup () {

    }

    @Test
    public void loginSuccesful() {
        AccountServices accountServices = new AccountServices();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setRequestURI("/login");
        request.setAttribute("email", "mejbox@davisstudies.com");
        request.setAttribute("password", "Passing");
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertEquals("Session Logged In Value Failed ", "successful", session.getAttribute("loggedIn"));
        assertEquals("Username value failed", session.getAttribute("logged_in_username"));
        assertNull("Error Messages not null", session.getAttribute("errors"));

    }

    @Test
    public void loginEmailFailed() {
        AccountServices accountServices = new AccountServices();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setRequestURI("/login");
        request.setAttribute("email", "mejbo@davisstudies.com");
        request.setAttribute("password", "Passing");
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertNotEquals("Session Logged In Value Failed ", "successful", session.getAttribute("loggedIn"));
        assertNotNull("Error Arrays Failed", session.getAttribute("errors"));
        assertTrue("Errors reason failure", ((ArrayList<String>) session.getAttribute("error")).contains("email"));

    }

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

    }
}
