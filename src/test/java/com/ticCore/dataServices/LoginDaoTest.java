package com.ticCore.dataServices;

import com.ticCore.beans.Login;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 10/4/16.
 */

public class LoginDaoTest {
    private static final String TEST_EMAIL = "testEmail@mail.com";
    private static final String TEST_PASSWORD = "testPass";
    private static final String TEST_FNAME = "testFirst";
    private Connection conn;

    private BaseDao loginDao;

    @Before
    public void setUp() throws Exception {
        loginDao = new LoginDao();
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TicGameTest",
                "root",
                "student");
    }

    @After
    public void tearDown() throws Exception {
        final Statement stmt = conn.createStatement();
        String deleteQuery = "DELETE FROM Players WHERE  email ='"+ TEST_EMAIL +"'";
        stmt.execute(deleteQuery);
        stmt.close();
        conn.close();

    }

    @Test
    public void testLogin() throws SQLException {


        final Statement stmt = conn.createStatement();
        String insertQuery = "INSERT INTO Players (first_name, email, password) VALUES ('"+TEST_FNAME+"','" +TEST_EMAIL +"','"+TEST_PASSWORD+"')";


        final boolean inserted = stmt.execute(insertQuery);
        HashMap<String, Object> criterions = new HashMap<String, Object>();
        HashMap<String, Object> emailCriteria = new HashMap<String, Object>();
        HashMap<String, Object> passCriteria = new HashMap<String, Object>();

        criterions.put("email", TEST_EMAIL);
        criterions.put("password", TEST_PASSWORD);
        List<Login> loginInfo =  (ArrayList<Login>) loginDao.getWithLimit(criterions,1) ;
        assertEquals("Did not fetch exactly one login info", 1, loginInfo.size());
        assertEquals("Wrong login info fetched", TEST_EMAIL, loginInfo.get(0).getEmail());



    }
}