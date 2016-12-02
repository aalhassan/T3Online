package com.ticCore.controllers;
import com.ticCore.beans.GameRecord;
import com.ticCore.dataServices.GameRecordsDao;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by student on 12/2/16.
 */
public class GameRecordServiceTest {
    private final Logger logger = Logger.getLogger(this.getClass());

    private static final String TEST_PLAYER = "testPlayer@mail.com";
    private static final String TEST_OPPONENT = "testOpponent@mail.com";
    private static final String WON = "Won";

    private Connection conn;
    private static MockHttpServletRequest request;

    private GameRecordService gameRecordService;
    private GameRecordsDao gameRecordsDao;

    @Before
    public void setup () throws  SQLException {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TicGameTest",
                "root",
                "student");
        gameRecordsDao = new GameRecordsDao();
        gameRecordService  = new GameRecordService();
        gameRecordService.setGameRecordsDao(gameRecordsDao);
        request = new MockHttpServletRequest();
        request.getSession().setAttribute(MainController.LOGGED_IN_EMAIL, TEST_PLAYER);
        request.getSession().setAttribute(MainController.LOGGED_IN, true);
        final Statement stmt = conn.createStatement();
        String deleteQuery = "DELETE  FROM game_records";
        logger.info(deleteQuery);
        stmt.execute(deleteQuery);
        GameRecord gameRecord = new GameRecord(TEST_PLAYER, WON, TEST_OPPONENT);
        gameRecordsDao.create(gameRecord);
    }

    @Test
    public void testGameRecordsFound() throws Exception {


        String redirectPage =  gameRecordService.getGameRecords(request);
        assertEquals("Records should exsist for player", true, request.getAttribute(MainController.FOUND_RECORD));
    }

    @Test
    public void testGameRecordsSize() throws Exception {
        String redirectPage =  gameRecordService.getGameRecords(request);
        List<GameRecord> gameRecords = (ArrayList<GameRecord>) request.getAttribute(MainController.GAME_RECORDS);
        assertEquals("There should be eaxactly one record for player", 1, gameRecords.size());
    }

    @Test
    public void testGameRecordsContent() throws Exception {
        String redirectPage =  gameRecordService.getGameRecords(request);
        List<GameRecord> gameRecords = (ArrayList<GameRecord>) request.getAttribute(MainController.GAME_RECORDS);
        assertEquals("Record should indicate a win", TEST_PLAYER, gameRecords.get(0).getPlayerId());
        assertEquals("Player should be navigated to myGames", "myGames", redirectPage);
    }

    @Test
    public void testGameRecordsNav() throws Exception {
        String redirectPage =  gameRecordService.getGameRecords(request);
        assertEquals("Player should be navigated to myGames", "myGames", redirectPage);
    }
}