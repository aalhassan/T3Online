package com.ticCore.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.Assert.*;

/**
 * Created by student on 10/17/16.
 */
public class MainControllerTest {
    private final static  String TEST_EMAIL = "mej1@hotmail.com";
    private final static  String TEST_PASS = "mockPassword";

    /*@Test
    public void getSessionAttributeWithoutGenericUtilTest() throws Exception {
        MockHttpSession session = new MockHttpSession();

        session.setAttribute("email",TEST_EMAIL );
        session.setAttribute("password",TEST_PASS);
        String sessEmail = session.getAttribute("email");
        String sessPass = session.getAttribute("email");
        assertEquals("Did not return correct email", TEST_EMAIL, sessEmail);
        assertEquals("Did not return correct email", TEST_PASS, sessPass);

    }*/
    @Test
    public void getSessionAttributeUsingGenericUtilTest() throws Exception {
        MockHttpSession session = new MockHttpSession();

        session.setAttribute("email",TEST_EMAIL );
        session.setAttribute("password",TEST_PASS);
        String sessEmail = MainController.getSessVal(session, "email");
        String sessPass = MainController.getSessVal(session, "password");
        assertEquals("Did not return correct email", TEST_EMAIL, sessEmail);
        assertEquals("Did not return correct email", TEST_PASS, sessPass);

    }

    @Test
    public void getReqAttributeUsingGenericUtilTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();

        request.setParameter("email",TEST_EMAIL );
        request.setParameter("password",TEST_PASS);
        String sessEmail = MainController.getReqParam(request, "email");
        String sessPass = MainController.getReqParam(request, "password");
        assertEquals("Did not return correct email", TEST_EMAIL, sessEmail);
        assertEquals("Did not return correct email", TEST_PASS, sessPass);

    }




}