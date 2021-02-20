package com.ironhack.opportunitiesservice.controller.dto;

import com.ironhack.opportunitiesservice.enums.Industry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CountryDTO {

    @NotNull
    @NotEmpty
    private int id;
    @NotNull
    @NotEmpty
    private String country;

    public CountryDTO() {
    }

    public CountryDTO(@NotNull @NotEmpty int id, @NotNull @NotEmpty String country) {
        setId(id);
        setCountry(country);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
