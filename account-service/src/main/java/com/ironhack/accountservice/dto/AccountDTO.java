package com.ironhack.accountservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountDTO {

    private Long id;
    @NotEmpty(message = "Industry can't be empty.")
    private String industry;
    @NotNull(message = "Employee Count can't be null.")
    @Min(value = 1, message = "Employee Count has to be over 0.")
    private int employeeCount;
    @NotEmpty(message = "City can't be empty.")
    private String city;
    @NotEmpty(message = "Country can't be empty.")
    private String country;


    public AccountDTO() {
    }

    public AccountDTO(@NotEmpty(message = "Industry can't be empty.") String industry, @NotNull(message = "Employee Count can't be null.") @Min(value = 1, message = "Employee Count has to be over 0.") int employeeCount, @NotEmpty(message = "City can't be empty.") String city, @NotEmpty(message = "Country can't be empty.") String country) {
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
    }

    public AccountDTO(Long id, @NotEmpty(message = "Industry can't be empty.") String industry, @NotNull(message = "Employee Count can't be null.") @Min(value = 1, message = "Employee Count has to be over 0.") int employeeCount, @NotEmpty(message = "City can't be empty.") String city, @NotEmpty(message = "Country can't be empty.") String country) {
        this(industry, employeeCount, city, country);
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
