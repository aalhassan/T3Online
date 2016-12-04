package com.ticCore.beans;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity to represent a player
 */
@Entity
@Table(name = "Players")
public class Player implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private int playerId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String password1;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String userName;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;

    public Player() {
    }


    /**Attributes constructor for player
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param answer1
     * @param answer2
     */
    public Player(String email, String password, String firstName, String lastName, String answer1, String answer2) {
        this();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return firstName + "_" + playerId;
    }


    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
}
