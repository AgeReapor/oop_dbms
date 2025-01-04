package com.dbms.models;

public class UserAccount {
    private final int USER_ID;
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String middlename;
    private int status;

    // Constructor
    public UserAccount(int userId, String username, String password, String lastname, String firstname,
            String middlename) {
        this.USER_ID = userId;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.status = 1;
    }

    // Getters and Setters

    public int getUserId() {
        return USER_ID;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
