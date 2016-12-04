package com.ticCore.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Entity bean to represent login info for a player
 */
@Entity
@Table(name = "Players")
public class Login {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")


    private int id;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
