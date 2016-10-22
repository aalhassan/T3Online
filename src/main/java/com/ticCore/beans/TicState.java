package com.ticCore.beans;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by student on 10/15/16.
 */
@XmlRootElement
public class TicState implements Serializable {

    private String gameSession;


    private char[][] board;  // The contents of the board. Values are ' ', 'X', or 'O' This variable is null before the first game starts.


    private String playerPlayingX;   // The ID of the player who is playing X.


    private String playerPlayingO;   // The ID of the player who is playing O.


    private String currentPlayer;    // The ID of the player who is to make the next move.


    private boolean gameEndedInTie;


    private String winner;


    private String connectionMessage;


    private String[] winCells;

    // empty constructor needed for deserialization by JAXB
    public TicState() {
    }

    @XmlElement
    public String[] getWinCells() {
        return winCells;
    }

    public void setWinCells(String[] winCells) {
        this.winCells = winCells;
    }

    @XmlElement
    public String getGameSession() {
        return gameSession;
    }

    public void setGameSession(String gameSession) {
        this.gameSession = gameSession;
    }

    @XmlElement
    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    @XmlElement
    public String getPlayerPlayingX() {
        return playerPlayingX;
    }

    public void setPlayerPlayingX(String playerPlayingX) {
        this.playerPlayingX = playerPlayingX;
    }

    @XmlElement
    public String getPlayerPlayingO() {
        return playerPlayingO;
    }

    public void setPlayerPlayingO(String playerPlayingO) {
        this.playerPlayingO = playerPlayingO;
    }

    @XmlElement
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @XmlElement
    public boolean isGameEndedInTie() {
        return gameEndedInTie;
    }

    public void setGameEndedInTie(boolean gameEndedInTie) {
        this.gameEndedInTie = gameEndedInTie;
    }

    @XmlElement
    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @XmlElement
    public String getConnectionMessage() {
        return connectionMessage;
    }

    public void setConnectionMessage(String connectionMessage) {
        this.connectionMessage = connectionMessage;
    }
}
