package com.ironhack.leadservice.controller.dto;


import com.ironhack.leadservice.model.Lead;

public class LeadDTO {


    // Properties
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private long repLead;

    // Constructor
    public LeadDTO(long id, String name, String phoneNumber, String email, String companyName, long repLead) {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setRepLead(repLead);

    }

    public LeadDTO(Lead lead) {
        this.id = lead.getId();
        this.companyName = lead.getCompanyName();
        this.email = lead.getEmail();
        this.name = lead.getName();
        this.phoneNumber = lead.getPhoneNumber();
        this.repLead = lead.getRepLead();

    }

    // -----------------Methods------------------

    // Override of the toString() method to display the Leads in a more friendly way.
    @Override
    public String toString() {
        return "id: " + id + " name: " + name +
                " | phoneNumber: " + phoneNumber +
                " | email: " + email +
                " | companyName: " + companyName;
    }

    //Getters & Setters
    public Long getRepLead() {
        return repLead;
    }

    public void setRepLead(Long repLead) {
        this.repLead = repLead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
