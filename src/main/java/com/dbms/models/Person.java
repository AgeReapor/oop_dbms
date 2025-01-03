package com.dbms.models;

public class Person {
    private final int PERSON_ID;
    private String lastName;
    private String firstName;
    private String middleName;

    // Constructor
    public Person(int personId, String lastName, String firstName, String middleName) {
        this.PERSON_ID = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    // Getters and Setters
    public int getPersonID() {
        return PERSON_ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // Methods
    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public String getProperName() {
        return lastName + ", " + firstName + " " + middleName;
    }

}
