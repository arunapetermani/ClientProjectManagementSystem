package com.example.demo.login.controller;

import java.io.Serializable;

public class Test implements Serializable {
    String username;
    String password;

    public Test() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
