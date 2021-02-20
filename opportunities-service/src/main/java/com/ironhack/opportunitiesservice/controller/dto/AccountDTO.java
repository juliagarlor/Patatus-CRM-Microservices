package com.ironhack.opportunitiesservice.controller.dto;

import com.ironhack.opportunitiesservice.enums.Industry;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

public class AccountDTO {

    private int id;
    private String industry;
    private int employeeCount;
    private String city;
    private String country;

    public AccountDTO() {
    }

    public AccountDTO(int id, String industry, int employeeCount, String city, String country) {
        this.id = id;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }

    public AccountDTO(String industry, int employeeCount, String city, String country) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
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
