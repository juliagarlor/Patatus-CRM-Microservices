package com.ironhack.contactservice.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountIdDTO {

    @NotNull
    private Long accountId;

    public AccountIdDTO() {
    }

    public AccountIdDTO(@NotNull Long accountId) {
        setAccountId(accountId);
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
