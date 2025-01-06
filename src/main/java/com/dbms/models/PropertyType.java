package com.dbms.models;

public enum PropertyType {
    OWNED("owned"),
    RENTED("rented"),
    LESSOR("lessor");

    public final String propertyType;

    // Constructor
    PropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    // Methods
    public static PropertyType fromString(String propertyType) {
        for (PropertyType type : PropertyType.values()) {
            if (type.propertyType.equals(propertyType.toLowerCase())) {
                return type;
            }
        }
        return null;
    }

    public String toString() {
        return propertyType;
    }
}
