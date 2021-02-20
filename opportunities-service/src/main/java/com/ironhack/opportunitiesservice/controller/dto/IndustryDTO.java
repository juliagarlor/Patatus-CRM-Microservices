package com.ironhack.opportunitiesservice.controller.dto;

import com.ironhack.opportunitiesservice.enums.Industry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IndustryDTO {

    @NotNull
    @NotEmpty
    private int id;
    @NotNull
    @NotEmpty
    private Industry industry;

    public IndustryDTO() {
    }

    public IndustryDTO(@NotNull @NotEmpty int id, @NotNull @NotEmpty Industry industry) {
       setId(id);
       setIndustry(industry);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }
}
