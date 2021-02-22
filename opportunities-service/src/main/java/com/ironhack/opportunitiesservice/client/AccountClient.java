package com.ironhack.opportunitiesservice.client;

import com.ironhack.opportunitiesservice.controller.dto.AccountDTO;
import com.ironhack.opportunitiesservice.enums.Industry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;

@FeignClient("accountService-dev")
public interface AccountClient {

    //This method return an account
    @GetMapping("/account/{accountId}")
    AccountDTO getAccountById(@PathVariable int accountId);
    @GetMapping("/accounts/country/{country}")
    List<Long> getAccountByCountry(@PathVariable String country);
    @GetMapping("/accounts/city/{city}")
    List<Long>getAccountByCity(@PathVariable String city);
    @GetMapping("/accounts/industry/{industry}")
    List<Long> getAccountByIndustry(@PathVariable String industry);

//    For the test
    @PostMapping("/account")
    AccountDTO postAccount(@RequestBody @Valid AccountDTO accountDTO);
    @GetMapping("/accounts")
    List<AccountDTO> getAllAccounts();
    @DeleteMapping("/accounts")
    void deleteAccounts();

//    For stats
    @GetMapping("/cities")
    List<String> getCities();
    @GetMapping("/countries")
    List<String> getCountries();
}
