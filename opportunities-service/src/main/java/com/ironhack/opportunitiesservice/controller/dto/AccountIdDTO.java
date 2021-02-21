package com.ironhack.opportunitiesservice.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountIdDTO {

    private @NotNull @NotEmpty Long id;

    public AccountIdDTO() {
    }

    public AccountIdDTO(@NotNull @NotEmpty Long id) {
        setId(id);
    }

    public @NotNull @NotEmpty Long getId() {
        return id;
    }

    public void setId(@NotNull @NotEmpty Long id) {
        this.id = id;
    }
}
