package com.dbms.models;

public enum PropertyType {
    OWNED("Owned"),
    RENTED("Rented"),
    LESSOR("Lessor");

    public final String propertyType;

    // Constructor
    PropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    // Getters
    public String getPropertyType() {
        return propertyType;
    }

    public String toString() {
        return propertyType;
    }
}
