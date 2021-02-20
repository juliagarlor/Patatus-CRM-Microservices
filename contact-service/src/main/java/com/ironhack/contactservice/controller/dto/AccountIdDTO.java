package com.ironhack.contactservice.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountIdDTO {

    @NotNull
    private int accountId;

    public AccountIdDTO() {
    }

    public AccountIdDTO(@NotNull int accountId) {
        setAccountId(accountId);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
