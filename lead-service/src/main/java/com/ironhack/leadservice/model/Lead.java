package com.ironhack.leadservice.model;


import javax.persistence.*;

@Entity
@Table(name = "leads")
public class Lead {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    @Column(name = "salesrep_id")
    private Long salesrepId;


    // Constructor
    public Lead(){
    }
    public Lead(String name, String phoneNumber, String email, String companyName, Long salesrepId) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setSalesrepId(salesrepId);
    }



    //Getters & Setters
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

    public Long getSalesrepId() {
        return salesrepId;
    }

    public void setSalesrepId(Long salesrepId) {
        this.salesrepId = salesrepId;
    }
}
