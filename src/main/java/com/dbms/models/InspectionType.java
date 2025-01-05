package com.dbms.models;

public enum InspectionType {
    NEW("new"),
    RENEWAL("renewal");

    public final String inspectionType;

    // Constructor
    InspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    // Getters
    public String getInspectionType() {
        return inspectionType;
    }

    // Methods
    public static InspectionType fromString(String inspectionType) {
        for (InspectionType type : InspectionType.values()) {
            if (type.inspectionType.equals(inspectionType.toLowerCase())) {
                return type;
            }
        }
        return null;
    }

    public String toString() {
        return inspectionType;
    }

}
