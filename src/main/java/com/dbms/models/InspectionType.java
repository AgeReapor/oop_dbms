package com.dbms.models;

public enum InspectionType {
    NEW("New"),
    RENEWAL("Renewal"),
    NONE("None");

    public final String inspectionType;

    // Constructor
    InspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    // Getters
    public String getInspectionType() {
        return inspectionType;
    }

    public String toString() {
        return inspectionType;
    }

}
