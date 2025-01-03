package com.dbms.models;

import java.time.LocalDate;

public class BusinessClearanceTransaction {
    private final int TRANSACTION_ID;
    private InspectionType inspectionType;
    private Business business;
    private Person inspector;
    private LocalDate inspectionDate;
    private double amount;
    private int status;

    // Constructor
    public BusinessClearanceTransaction(int transactionId, InspectionType inspectionType, Business business,
            Person inspector, LocalDate inspectionDate, double amount) {
        this.TRANSACTION_ID = transactionId;
        this.inspectionType = inspectionType;
        this.business = business;
        this.inspector = inspector;
        this.inspectionDate = inspectionDate;
        this.amount = amount;
        this.status = 1;
    }

    // Getters and Setters
    public int getTransactionId() {
        return TRANSACTION_ID;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Person getInspector() {
        return inspector;
    }

    public void setInspector(Person inspector) {
        this.inspector = inspector;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
