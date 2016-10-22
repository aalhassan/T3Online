package com.ticCore.restServices;

import com.ticCore.beans.TicState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;


/**
 * Created by student on 10/15/16.
 */
public class TicGameEngineTest {
    private TicGameEngine ticEngine;
    private static final String PLAYER1 = "nas@mail.com";
    private static final String PLAYER2 = "nas1@mail.com";
    @Before
    public void setUp() throws Exception {
        ticEngine = new TicGameEngine("GameSession1", null);

    }

    @Test
    public void gameStartTest() throws Exception {
       /* ticEngine.setPlayerPlayingO(PLAYER1);
        ticEngine.setPlayerPlayingX(PLAYER2);
        assertEquals("Missing updates", 2, ticEngine.updatesSent);
        TicState ticState = ticEngine.getTicState();
        String currentPlayer = ticState.getCurrentPlayer();
        assertNotNull("ticState does not have current player", currentPlayer);
        assertTrue("Wrong current player", currentPlayer.equals(PLAYER1)
                || currentPlayer.equals(PLAYER2));
*/
    }
}