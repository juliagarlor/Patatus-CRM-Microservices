package com.ironhack.contactservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("accountService-dev")
public interface AccountClient {

    @GetMapping("/account/{id}/id")
    Long getAccountId (@PathVariable Long id);

}
