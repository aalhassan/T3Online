package com.ticCore.restServices;

import com.ticCore.controllers.MainController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

/**
 * Created by student on 12/2/16.
 */
public class GameServerTest {
    private GameServer gameServer;
    private MockHttpServletRequest request1;
    private MockHttpServletRequest request2;
    private static final String TEST_PLAYER1= "testEmail@mail.com";
    private static final String TEST_PLAYER2 = "testEmail2@mail.com";
    @Before
    public void setUp() throws Exception {

        gameServer  = new GameServer();
    }

    @Test
    public void registerForNewGameTest() throws Exception {
        request1 = new MockHttpServletRequest();
        request1.getSession().setAttribute(MainController.LOGGED_IN_EMAIL, TEST_PLAYER1);
        request1.getSession().setAttribute(MainController.LOGGED_IN, true);
        gameServer.registerForNewGame(request1);
        assertEquals("Connection Idx not incremented", 1, gameServer.getConnectionIdx().get());
        request2 = new MockHttpServletRequest();
        request2.getSession().setAttribute(MainController.LOGGED_IN_EMAIL, TEST_PLAYER2);
        request2.getSession().setAttribute(MainController.LOGGED_IN, true);
        gameServer.registerForNewGame(request2);
        assertEquals("Connection Idx not incremented", 2, gameServer.getConnectionIdx().get());
        assertTrue("Game Session not set", gameServer.getCurrentGames().containsKey("GameSession1"));
    }


}