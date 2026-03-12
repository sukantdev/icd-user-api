package com.corpay.dto;

public class Profile {
    private String emailId;
    private String firstName;
    private String lastName;
    private String brand;
    private String externalUser;
    
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getExternalUser() {
        return externalUser;
    }
    public void setExternalUser(String externalUser) {
        this.externalUser = externalUser;
    }

    
}
