package com.ticCore.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Entity bean to represent game records for a players
 */
@Entity
@Table(name = "game_records")
public class GameRecord {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "record_id")
    private int gameId;

    @Column(name = "player_id")
    private String playerId;

    @Column(name = "result")
    private String result;

    @Column(name = "opponent_id")
    private String opponentId;

    public GameRecord() {
    }

    public GameRecord(String playerId, String result, String opponentId) {
        this();
        this.playerId = playerId;
        this.result = result;
        this.opponentId = opponentId;
    }
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
    }


}
