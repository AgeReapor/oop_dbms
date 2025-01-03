package com.dbms.models;

public class UserAccount {
    private final int USER_ID;
    private String username;
    private String password;
    private Person person;
    private int status;

    // Constructor
    public UserAccount(int userId, String username, String password, Person person) {
        this.USER_ID = userId;
        this.username = username;
        this.password = password;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
