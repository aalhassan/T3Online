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
        ticEngine = new TicGameEngine("GameSession1", new GameServer());

    }

    @Test
    public void gameStartTest() throws Exception {
      ticEngine.setPlayerPlayingO(PLAYER1);
        ticEngine.setPlayerPlayingX(PLAYER2);
        assertEquals("Missing updates", 2, ticEngine.updatesSent);
        TicState ticState = ticEngine.getTicState();
        String currentPlayer = ticState.getCurrentPlayer();
        assertNotNull("ticState does not have current player", currentPlayer);
        assertTrue("Wrong current player", currentPlayer.equals(PLAYER1)
                || currentPlayer.equals(PLAYER2));

    }

    @Test
    public void simulateGameWinTest() throws Exception {
        ticEngine.setPlayerPlayingO(PLAYER1);
        ticEngine.setPlayerPlayingX(PLAYER2);
        TicState gameState = ticEngine.getTicState();
        ticEngine.processInputs(PLAYER1, new int[]{0,0});
        ticEngine.processInputs(PLAYER2, new int[]{0,1});
        ticEngine.processInputs(PLAYER1, new int[]{1,0});
        ticEngine.processInputs(PLAYER2, new int[]{1,1});
        assertNull("There should not be a winner at this point", gameState.getWinner());
        Thread.sleep(2000L);
        ticEngine.processInputs(PLAYER1, new int[]{2,0});
        //assertEquals("Player1 should have won", PLAYER1, gameState.getWinner());
    }
}