package com.ticCore.restServices;

import com.ticCore.beans.GameRecord;
import com.ticCore.beans.TicState;
import com.ticCore.dataServices.BaseDao;
import com.ticCore.dataServices.GameRecordsDao;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;

import javax.print.attribute.standard.Media;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by student on 10/14/16.
 */
public class TicGameEngine  implements  GameEngine {

    private TicState ticState ; //Entity encapsulates the state of the game
    private boolean gameInProgress;  // True while a game is being played;
    private static final String WAIT_MESSAGE = "Connected, waiting for second player...";
    private static final String CONNECTED_MESSAGE = "Game in progress... You are playing ";
    private static final String TIE = "Tie";
    private static final String WON = "Won";
    private static final String LOST = "Lost";
    public int updatesSent;
    public final TicBroadCaster TIC_BROADCASTER = new TicBroadCaster(this);
    private GameServer gameServer;
    private BaseDao gameRecordsDao ;
    private Logger logger = Logger.getLogger(this.getClass());


    /**
     * @param gameSession Session to which this game engine belongs
     */
    public TicGameEngine(String gameSession) {
        gameRecordsDao = new GameRecordsDao();
        ticState = new TicState();
        ticState.setGameSession(gameSession);
    }

    /**
     * @param gameSession Session to which this game engine belongs
     * @param gameServer Game Server where this game engine is used
     */
    public TicGameEngine(String gameSession, GameServer gameServer) {
        this(gameSession);
        this.gameServer = gameServer;
    }

    public TicState getTicState() {
        return ticState;
    }

    public void setPlayerPlayingX(String playerPlayingX) {
        if (ticState.getPlayerPlayingX() != null || playerPlayingX.equals(ticState.getPlayerPlayingO()) )
            return;

        ticState.setPlayerPlayingX(playerPlayingX);
        if (ticState.getPlayerPlayingO() != null) {
            ticState.setConnectionMessage(CONNECTED_MESSAGE);
            startGame();
        }
        else {
            ticState.setConnectionMessage(WAIT_MESSAGE);
            sendGameState();
        }
    }


    public void setPlayerPlayingO(String playerPlayingO) {
        if (ticState.getPlayerPlayingO() != null || playerPlayingO.equals(ticState.getPlayerPlayingX()) )
            return;

        ticState.setPlayerPlayingO(playerPlayingO);
        if (ticState.getPlayerPlayingX() != null) {
            ticState.setConnectionMessage(CONNECTED_MESSAGE);
            startGame();
        }
        else {
            ticState.setConnectionMessage(WAIT_MESSAGE);
            sendGameState();
        }
    }

    //----------- the method that is called by the server to react to messages from the players -----------

    /**
     *  Respond to a message that was sent by one of the players to the hub.
     *  Note that illegal messages (of the wrong type or coming at an illegal
     *  time) are simply ignored.  The messages that are understood are
     *  the string "newgame" for starting a new game and an array of two
     *  ints giving the row and column of a move that the user wants to make.
     *  @param sender the ID number of the player who sent the message.
     *  @param message the message that was received from that player.
     */
    public <T> void processInputs(String sender, T message) {
        if (!gameInProgress || !(message instanceof int[]) || !sender.equals(ticState.getCurrentPlayer()))
            return;
            // The message represents a move by the current player.
            int[] move = (int[])message;
            if (move.length != 2)
                return;
            int row = move[0];
            int col = move[1];
            char[][] board = ticState.getBoard();
            if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ')
                return;
            board[row][col] = (ticState.getCurrentPlayer().equals(ticState.getPlayerPlayingX()))?
                        'X' : 'O'; // Make the move.
            ticState.setBoard(board);
            if (winner()) { // CurrentPlayer has won.

                gameInProgress = false;
                ticState.setWinner(ticState.getCurrentPlayer());
                saveRecord(false);
            }
            else if (tie()) { // The board is full but there is no winner; game ends in a tie.
                gameInProgress = false;
                ticState.setGameEndedInTie(true);
                saveRecord(true);
            }
            else {  // It's the other player's turn now.
                String currentPlayer = ticState.getCurrentPlayer();
                String playerPlayingX = ticState.getPlayerPlayingX();
                String playerPlayingO = ticState.getPlayerPlayingO();
                ticState.setCurrentPlayer(currentPlayer.equals(playerPlayingX)? playerPlayingO : playerPlayingX);
            }
        sendGameState();


    }

    /**Persists records of this game after a win, tie or loss
     * @param isTie if game ended in a tie
     */
    private void saveRecord(boolean isTie) {
        try {
            if (isTie) {
                gameRecordsDao.create(new GameRecord(ticState.getPlayerPlayingX(), TIE, ticState.getPlayerPlayingO()));
                gameRecordsDao.create(new GameRecord(ticState.getPlayerPlayingO(), TIE, ticState.getPlayerPlayingX()));
            } else if (ticState.getPlayerPlayingX().equals(ticState.getWinner())) {
                gameRecordsDao.create(new GameRecord(ticState.getPlayerPlayingX(), WON, ticState.getPlayerPlayingO()));
                gameRecordsDao.create(new GameRecord(ticState.getPlayerPlayingO(), LOST, ticState.getPlayerPlayingX()));
            } else {
                gameRecordsDao.create(new GameRecord(ticState.getPlayerPlayingX(), LOST, ticState.getPlayerPlayingO()));
                gameRecordsDao.create(new GameRecord(ticState.getPlayerPlayingO(), WON, ticState.getPlayerPlayingX()));
            }
        }catch (Exception ex) {
            logger.info(ex.getMessage());
           
        }
    }

    /**
     * This package private method is called by the hub when the second player
     * connects.  It's purpose is to start the first game.
     */
    public void startGame() {
        startNewGame();
        sendGameState();
    }


    //------------------- Some private utility methods used by the apply() method ---------------

    /**
     * Start a game.  Board is initialized to empty.  Players are
     * randomly assigned to play 'X' or 'O'.
     */
    private void startNewGame() {
        ticState.setBoard (new char[3][3]);
        char [][] board = ticState.getBoard();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        ticState.setBoard(board);
        ticState.setCurrentPlayer((Math.random() < 0.5)? ticState.getPlayerPlayingX() :
                ticState.getPlayerPlayingO());
        ticState.setGameEndedInTie(false);
        gameInProgress = true;
    }

    /**
     * Check if there is a winner, i.e. three pieces of the same kind in a row.
     */
    private boolean winner() {
        char[][] board = ticState.getBoard();
        if (board[0][0] != ' ' &&
                (board[0][0] == board[1][1] && board[1][1] == board[2][2])) {
            ticState.setWinCells(new String[]{"00", "11", "22"});
            return true;
        }
        if (board[0][2] != ' ' &&
                (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            ticState.setWinCells(new String[]{"02", "11", "20"});
            return true;
        }
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != ' ' &&
                    (board[row][0] == board[row][1] && board[row][1] == board[row][2])) {
                ticState.setWinCells(new String[]{row + "0", row + "1", row + "2"});
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != ' ' &&
                    (board[0][col] == board[1][col] && board[1][col] == board[2][col])) {
                ticState.setWinCells(new String[]{"0" + col, "1" + col, "2" + col});
                return true;
            }
        }
        return false;
    }
    /**
     * Check if the board is full.  (This is called after the winner method
     * has returned false, so a full board means that the game is a tie.)
     */
    private boolean tie() {
        char[][] board = ticState.getBoard();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    public  void sendGameState() {
        if (gameServer == null)
            return;
        gameServer.broadcastMessage(ticState.getGameSession(), ticState);
        updatesSent++;
    }

    public void playerDisconnected(String playerId) {
        String winnerId = playerId.equals(ticState.getPlayerPlayingX())? ticState.getPlayerPlayingX():
                    ticState.getPlayerPlayingO();
        endGame(winnerId);
    }

    /** Ends game engine
     * @param winnerId id of winner
     */

    private void endGame(String winnerId) {
        ticState.setWinner(winnerId);
        gameInProgress = false;
        saveRecord(false);
        sendGameState();
    }

}
