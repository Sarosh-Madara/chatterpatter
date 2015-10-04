package com.example.saroshmadara.chatterpatter.models;

/**
 * Created by Sarosh Madara on 23-09-2015.
 */
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String lastLogin;
    private String status;
    private String token; //245 digits firebase access token string, this will also be used as our token,
    private String userID;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public User() {
    }

    // Will be used when user sign up
    public User(String email, String userID, String firstName, String lastName,String password) {
        this.email = email;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String email, String firstName, String lastName, String lastLogin, String status, String token, String userID) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLogin = lastLogin;
        this.status = status;
        this.token = token;
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
