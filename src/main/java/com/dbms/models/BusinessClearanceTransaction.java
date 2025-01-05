package com.dbms.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BusinessClearanceTransaction {
    private final int TRANSACTION_ID;
    private InspectionType inspectionType;
    private String owner;
    private String ownerAddress;
    private String businessName;
    private String businessAddress;
    private String businessType;
    private String contactNumber;
    private PropertyType propertyType;
    private String registrationNumber;
    private String inspector;
    private LocalDate inspectionDate;
    private BigDecimal amount;
    private String officialReceiptNumber;
    private int status;

    // Constructor
    public BusinessClearanceTransaction(int transactionId, InspectionType inspectionType, String owner,
            String ownerAddress, String businessName, String businessAddress, String businessType, String contactNumber,
            PropertyType propertyType,
            String registrationNumber, String inspector, LocalDate inspectionDate, BigDecimal amount,
            String officialReceiptNumber) {
        this.TRANSACTION_ID = transactionId;
        this.inspectionType = inspectionType;
        this.owner = owner;
        this.ownerAddress = ownerAddress;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessType = businessType;
        this.contactNumber = contactNumber;
        this.propertyType = propertyType;
        this.registrationNumber = registrationNumber;
        this.inspector = inspector;
        this.inspectionDate = inspectionDate;
        this.amount = amount;
        this.officialReceiptNumber = officialReceiptNumber;
        this.status = 1;
    }

    public BusinessClearanceTransaction() {
        this.TRANSACTION_ID = -1;
        this.inspectionType = null;
        this.owner = "";
        this.ownerAddress = "";
        this.businessName = "";
        this.businessAddress = "";
        this.businessType = "";
        this.contactNumber = "";
        this.propertyType = null;
        this.registrationNumber = "";
        this.inspector = "";
        this.inspectionDate = null;
        this.amount = new BigDecimal(0);
        this.officialReceiptNumber = "";
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

    public void setInspectionType(String inspectionTypeStr) {
        this.inspectionType = InspectionType.fromString(inspectionTypeStr);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public void setPropertyType(String propertyTypeStr) {
        this.propertyType = PropertyType.fromString(propertyTypeStr);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOfficialReceiptNumber() {
        return officialReceiptNumber;
    }

    public void setOfficialReceiptNumber(String officialReceiptNumber) {
        this.officialReceiptNumber = officialReceiptNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
