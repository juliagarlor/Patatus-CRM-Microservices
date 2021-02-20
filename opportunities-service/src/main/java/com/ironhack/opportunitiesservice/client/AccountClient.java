package com.ironhack.opportunitiesservice.client;

import com.ironhack.opportunitiesservice.controller.dto.AccountDTO;
import com.ironhack.opportunitiesservice.enums.Industry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("accountService-dev")
public interface AccountClient {

    //This method return an acount
    @GetMapping("/account/{accountId}")
    AccountDTO getAccountById(@PathVariable int accountId);
    @GetMapping("/accounts/country/{country}")
    List<Long> getAccountByCountry(@PathVariable String country);
    @GetMapping("/accounts/city/{city}")
    List<Long>getAccountByCity(@PathVariable String city);
    @GetMapping("/accounts/industry/{industry}")
    List<Long> getAccountByIndustry(@PathVariable Industry industry);

}
