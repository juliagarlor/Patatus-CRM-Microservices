package com.ironhack.opportunitiesservice.controller.dto;

import com.ironhack.opportunitiesservice.enums.Industry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CityDTO {

    @NotNull
    @NotEmpty
    private int id;
    @NotNull
    @NotEmpty
    private String city;

    public CityDTO() {
    }

    public CityDTO(@NotNull @NotEmpty int id, @NotNull @NotEmpty String city) {
        setId(id);
        setCity(city);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
