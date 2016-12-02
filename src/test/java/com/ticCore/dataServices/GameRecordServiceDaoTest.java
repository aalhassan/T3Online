package com.ticCore.dataServices;

import com.ticCore.beans.GameRecord;
import com.ticCore.beans.Login;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 11/18/16.
 */
public class GameRecordServiceDaoTest {
    private final Logger logger = Logger.getLogger(this.getClass());

    private static final String TEST_PLAYER = "testPlayer@mail.com";
    private static final String TEST_OPPONENT = "testOpponent@mail.com";
    private static final String TIE = "Tie";
    private static final String WON = "Won";
    private static final String LOST = "Lost";
    private Connection conn;

    private BaseDao gameRecordsDao;

    @Before
    public void setUp() throws Exception {
        gameRecordsDao = new GameRecordsDao();
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TicGameTest",
                "root",
                "student");
    }


    @Test
    public void testRecordCreate() throws SQLException {
        GameRecord gameRecord = new GameRecord(TEST_PLAYER, TIE, TEST_OPPONENT);
        gameRecordsDao.create(gameRecord);

        final Statement stmt = conn.createStatement();
        String selectQuery = "SELECT * FROM  game_records WHERE player_id = '" + TEST_PLAYER + "' AND result = '" + TIE + "' AND opponent_id = '" + TEST_OPPONENT + "'";
        logger.info(selectQuery);
        final ResultSet resultSet = stmt.executeQuery(selectQuery);
        while (resultSet.next()){
            assertEquals("Did not fetch right game record", gameRecord.getOpponentId(), resultSet.getString("opponent_id"));
            assertEquals("Did not fetch right game record", gameRecord.getPlayerId(), resultSet.getString("player_id"));
            assertEquals("Did not fetch right game record", gameRecord.getResult(), resultSet.getString("result"));
        }
    }

    @Test
    public void testRecordFetch() throws SQLException {

    final Statement stmt = conn.createStatement();
        String deleteQuery = "DELETE  FROM game_records";
        logger.info(deleteQuery);
        stmt.execute(deleteQuery);
        GameRecord gameRecord = new GameRecord(TEST_PLAYER, TIE, TEST_OPPONENT);
        gameRecordsDao.create(gameRecord);

        HashMap<String, Object> criterions = new HashMap<String, Object>();
        criterions.put("playerId", TEST_PLAYER);
        criterions.put("opponentId", TEST_OPPONENT);

        final List<GameRecord> recordsFetched = (ArrayList<GameRecord>) gameRecordsDao.get(criterions);


        assertEquals("Did not fetch exactly one record", 1, recordsFetched.size());
        assertEquals("Did not fetch right record", TEST_OPPONENT, recordsFetched.get(0).getOpponentId());

    }

    @After
    public void tearDown() throws Exception {
        final Statement stmt = conn.createStatement();
        String deleteQuery = "DELETE  FROM game_records WHERE  player_id = '"+ TEST_PLAYER +"'";
        stmt.execute(deleteQuery);
        stmt.close();
        conn.close();
    }


}