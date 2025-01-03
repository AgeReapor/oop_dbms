package com.dbms.models;

public class Address {
    private final int ADDRESS_ID;
    private String street;
    private String barangay;
    private String city;
    private String province;

    // Constructor
    public Address(int addressId, String street, String barangay, String city, String province) {
        this.ADDRESS_ID = addressId;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
    }

    // Getters and Setters
    public int getAddressId() {
        return ADDRESS_ID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    // Methods
    public String getFullAddress() {
        return street + ", " + barangay + ", " + city + ", " + province;
    }

}
