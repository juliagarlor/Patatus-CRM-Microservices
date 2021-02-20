package com.ironhack.contactservice.controller.dto;

import javax.validation.constraints.NotNull;

public class AccountIdDTO {

    private @NotNull Long accountId;

    public AccountIdDTO() {
    }

    public AccountIdDTO(@NotNull Long accountId) {
        setAccountId(accountId);
    }

    public @NotNull Long getAccountId() {
        return accountId;
    }

    public void setAccountId(@NotNull Long accountId) {
        this.accountId = accountId;
    }
}
