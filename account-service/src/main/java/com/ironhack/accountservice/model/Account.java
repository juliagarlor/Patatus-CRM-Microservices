package com.ironhack.accountservice.model;

import com.ironhack.accountservice.enums.Industry;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private Integer employeeCount;
    private String city;
    private String country;

    // Constructor

    public Account() {
    }

    public Account(Industry industry, Integer employeeCount, String city, String country) {
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
    }



    // Override of the toString() method to display the Accounts in a more friendly way.
//    @Override
//    public String toString() {
//        return "---------Account " + id +"---------" +
//                "\n" +
//                "Industry: " + industry +
//                " | employeeCount: " + employeeCount +
//                " | city: " + city +
//                " | country: " + country;
//    }

    // Getters & Setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}