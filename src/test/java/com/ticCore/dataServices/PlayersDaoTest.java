package com.ticCore.dataServices;

import com.ticCore.beans.Login;
import com.ticCore.beans.Player;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 12/2/16.
 */
public class PlayersDaoTest {
    private static final String TEST_EMAIL = "testEmail@mail.com";
    private static final String TEST_PASSWORD = "testPass";
    private static final String UPDATE_PASSWORD = "updatePass";
    private static final String TEST_FNAME = "testFirst";
    private Connection conn;
    private Logger logger = Logger.getLogger(this.getClass());
    private BaseDao playersDao;

    @Before
    public void setUp() throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TicGameTest",
                "root",
                "student");
        final Statement stmt = conn.createStatement();
        String deleteQuery = "DELETE  FROM Players";
        stmt.execute(deleteQuery);
        playersDao = new PlayersDao();
    }



    @Test
    public void getPlayerTest() throws Exception {
        final Statement stmt = conn.createStatement();
        String insertQuery = "INSERT INTO Players (first_name, email, password) VALUES ('"+TEST_FNAME+"','" +TEST_EMAIL +"','"+TEST_PASSWORD+"')";

        final boolean inserted = stmt.execute(insertQuery);
        HashMap<String, Object> criterions = new HashMap<String, Object>();


        criterions.put("email", TEST_EMAIL);
        criterions.put("password", TEST_PASSWORD);
        List<Player> players =  (ArrayList<Player>) playersDao.getAll() ;
        assertEquals("Did not fetch exactly one login info", 1, players.size());
        assertEquals("Wrong login info fetched", TEST_EMAIL, players.get(0).getEmail());
    }

    @Test
    public void createAndUpdatePlayerTest() throws Exception {
        Player testPlayer = new Player(TEST_EMAIL,  TEST_PASSWORD, TEST_FNAME, "testlast", "Mom", "Matrix");
        playersDao.create(testPlayer);
        Thread.sleep(1000L);

        final Statement stmt = conn.createStatement();
        String selectQuery = "SELECT * FROM  Players WHERE email = '" + TEST_EMAIL + "'";
        logger.info(selectQuery);
        final ResultSet resultSet = stmt.executeQuery(selectQuery);
        int numPlayers = 0;
        while (resultSet.next()){
            assertEquals("Wrong record created", TEST_EMAIL, resultSet.getString("email"));
            assertEquals("Wrong record created", TEST_PASSWORD, resultSet.getString("password"));
            numPlayers++;
        }
        assertEquals("Wrong number of records fetched", 1, numPlayers);
        testPlayer.setPassword(UPDATE_PASSWORD);
        playersDao.update(testPlayer);

        //final Statement stmt2 = conn.createStatement();
        selectQuery = "SELECT * FROM  Players WHERE email = '" + TEST_EMAIL + "'";
        logger.info(selectQuery);
        final ResultSet updateRS = stmt.executeQuery(selectQuery);

        while (updateRS.next()){
            assertEquals("Update failed", UPDATE_PASSWORD, updateRS.getString("password"));
        }
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