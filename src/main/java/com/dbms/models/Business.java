package com.dbms.models;

public class Business {
    private final int BUSINESS_ID;
    private String businessName;
    private String businessType;
    private Person owner;
    private Address ownerAddress;
    private String contactNumber;
    private PropertyType propertyType;
    private String registrationNo;

    // Constructor
    public Business(int businessId, String businessName, String businessType, Person owner, Address ownerAddress,
            String contactNumber, PropertyType propertyType, String registrationNo) {
        this.BUSINESS_ID = businessId;
        this.businessName = businessName;
        this.businessType = businessType;
        this.owner = owner;
        this.ownerAddress = ownerAddress;
        this.contactNumber = contactNumber;
        this.propertyType = propertyType;
        this.registrationNo = registrationNo;
    }

    // Getters and Setters
    public int getBusinessId() {
        return BUSINESS_ID;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Address getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(Address ownerAddress) {
        this.ownerAddress = ownerAddress;
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

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

}
