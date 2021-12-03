package com.example.inventory.data.model;

public class User {

    private String email;
    private String password;

    public User(String user, String password) {
        this.email = user;
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
