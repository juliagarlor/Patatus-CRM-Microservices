package com.ironhack.leadservice.model;


import com.ironhack.leadservice.controller.dto.LeadDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Lead {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private long repLead;


    // Constructor
    public Lead(String name, String phoneNumber, String email, String companyName, long repLead) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setRepLead(repLead);
    }

//    public Lead(LeadDTO lead){
//        setName(lead.getName());
//        setPhoneNumber(lead.getPhoneNumber());
//        setEmail(lead.getEmail());
//        setCompanyName(lead.getCompanyName());
//        setRepLead(lead.getRepLead());
//    }

    public Lead(){}

    // -----------------Methods------------------

    // Override of the toString() method to display the Leads in a more friendly way.
    @Override
    public String toString() {
        return "ID-" + id +
                " | name: " + name +
                " | phoneNumber: " + phoneNumber +
                " | email: " + email +
                " | companyName: " + companyName;
    }

    //Getters & Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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


    public long getRepLead() {
        return repLead;
    }

    public void setRepLead(long repLead) {
        this.repLead = repLead;
    }



}
