package com.ironhack.contactservice.model;

import javax.persistence.*;

@Entity
public class Contact {
    // Properties:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    private /*@javax.validation.constraints.NotNull*/ Long accountId;


    // Constructors:
    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email, String companyName) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    public Contact(String name, String phoneNumber, String email, String companyName,/* @javax.validation.constraints.NotNull */Long accountId) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setAccountId(accountId);
    }


    // -----------------Methods------------------

    // Override of the toString() method to display the Contacts in a more friendly way.
    @Override
    public String toString() {
        return "ID-" + id +
                " | name: " + name +
                " | phoneNumber: " + phoneNumber +
                " | email: " + email +
                " | companyName: " + companyName;
    }

    // Getters & Setters:
    public Long getId() {
        return id;
    }

    public void setId() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public /*@javax.validation.constraints.NotNull*/ Long getAccountId() {
        return accountId;
    }

    public void setAccountId(/*@javax.validation.constraints.NotNull*/ Long accountId) {
        this.accountId = accountId;
    }
}
