package com.example.clarinetmaster.finalexamcpsu2016.Models;

import java.io.Serializable;

public class Users implements Serializable {

    private final String name;
    private final String username;
    private final String password;

    public Users(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
