package com.example.leadservice.controller.dto;

import com.example.leadservice.model.Lead;

public class LeadDTO {

    // Properties
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    // Constructor
    public LeadDTO(String name, String phoneNumber, String email, String companyName) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    public LeadDTO(Lead lead) {

        this.companyName = lead.getCompanyName();
        this.email = lead.getEmail();
        this.name = lead.getName();
        this.phoneNumber = lead.getPhoneNumber();

    }

    // -----------------Methods------------------

    // Override of the toString() method to display the Leads in a more friendly way.
    @Override
    public String toString() {
        return " name: " + name +
                " | phoneNumber: " + phoneNumber +
                " | email: " + email +
                " | companyName: " + companyName;
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
