package com.ticCore.controllers;

import com.ticCore.controllers.LoginServices;
import com.ticCore.dataServices.PlayersDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
/**
 * Created by student on 9/10/16.
 */

public class LoginServicesTest {
    LoginServices loginServices;
    private static final String LOGIN_PATH = "/login";
    private Connection conn;
    private static final String TEST_EMAIL = "testEmail@mail.com";
    private static final String TEST_PASSWORD = "testPass";
    private static final String TEST_FNAME = "testFirst";

    @Before
    public void setup () throws  Exception {
        loginServices = new LoginServices();
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TicGameTest",
                "root",
                "student");
        final Statement stmt = conn.createStatement();
        String deleteQuery = "DELETE FROM Players";
        stmt.execute(deleteQuery);
        String insertQuery = "INSERT INTO Players (first_name, email, password) VALUES ('"+TEST_FNAME+"','" +TEST_EMAIL +"','"+TEST_PASSWORD+"')";
        stmt.executeUpdate(insertQuery);
    }

    @Test
    public void loginSuccessful() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setParameter("email", TEST_EMAIL);
        request.setParameter("password", TEST_PASSWORD);
        loginServices.login(request);

        assertEquals("Session LoggedIn attribute Failed ",true,  MainController.getSessVal(session,MainController.LOGGED_IN));
        assertEquals("Username value failed",TEST_EMAIL, session.getAttribute(MainController.LOGGED_IN_EMAIL));
        assertNull("RequestErrors is to be null",  (HashMap<String,String>) request.getAttribute("errors"));

    }

   @Test
    public void loginFailedIfEmailMissing() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setParameter("password", "firstPass");


        loginServices.login(request);

        assertNull("Session LoggedIn attribute Failed ",  session.getAttribute(MainController.LOGGED_IN));
        assertTrue("Errors should contain email",  ((HashMap<String,String>) request.getAttribute("errors")).containsKey("email"));

    }

    @Test
    public void loginFailedIfPasswordMissing() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setParameter("email", TEST_EMAIL);


        loginServices.login(request);

        assertNull("Session LoggedIn attribute Failed ",  session.getAttribute("loggedIn"));
        assertTrue("Errors should contain password",  ((HashMap<String,String>) request.getAttribute("errors")).containsKey("password"));
    }

    @Test
    public void loginAuthFailed() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        request.setRequestURI(LOGIN_PATH);
        request.setParameter("email", "realmej@hotmail.com");
        request.setParameter("password", "wrongPass");
        MockHttpServletResponse response = new MockHttpServletResponse();

        loginServices.login(request);

        assertNull("Session LoggedIn attribute Failed ",  session.getAttribute("loggedIn"));
        assertTrue("Errors should contain auth",  ((HashMap<String,String>) request.getAttribute("errors")).containsKey("auth"));
    }

    @After
    public void tearDown() throws Exception {
        final Statement stmt = conn.createStatement();
        String deleteQuery = "DELETE  FROM Players WHERE  email = '"+ TEST_EMAIL +"'";
        stmt.execute(deleteQuery);
        stmt.close();
        conn.close();
    }

}
