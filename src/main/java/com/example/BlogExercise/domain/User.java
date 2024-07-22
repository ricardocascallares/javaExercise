package com.example.BlogExercise.domain;

import jakarta.persistence.*;

@Entity
public class User {


    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    public Long userId;
    public String username;
    public String lastname;

    public User(){};

    public User(String username, String lastname) {
        this.username = username;
        this.lastname = lastname;
    }

    public String toString(){
        return "{ \"username\": \"" + username + "\", \"lastname\": \"" + lastname + "\"}";
    }

}
