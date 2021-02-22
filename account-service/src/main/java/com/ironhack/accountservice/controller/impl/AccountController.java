package com.ironhack.accountservice.controller.impl;

import com.ironhack.accountservice.controller.interfaces.IAccountController;
import com.ironhack.accountservice.dto.AccountDTO;
import com.ironhack.accountservice.model.Account;
import com.ironhack.accountservice.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    IAccountService accountService;

    //    todo, te cambio para que devuelva un dto y poder pasarselo a opportunities para los tests
    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO postAccount(@RequestBody @Valid AccountDTO accountDTO) {
        return accountService.postAccount(accountDTO);
    }

//    todo, te cambio para que devuelva un dto
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable() Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/accounts/country/{country}")
    public List<Long> getAccountsByCountry(@PathVariable String country) {
        return accountService.getAccountsByCountry(country);
    }

    @GetMapping("/accounts/city/{city}")
    public List<Long> getAccountsByCity(@PathVariable String city) {
        return accountService.getAccountsByCity(city);
    }

    @GetMapping("/accounts/industry/{industry}")
    public List<Long> getAccountsByIndustry(@PathVariable String industry) {
        return accountService.getAccountsByIndustry(industry);
    }

//    TODO: julia: Perdón por el intrusismo, pero lo necesito para los tests
    @DeleteMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccounts(){
        accountService.deleteAccounts();
    }



    // Routes for stats:
    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getCities(){
        return accountService.getCities();
    }

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getCountries(){
        return accountService.getCountries();
    }

    @GetMapping("/stats/mean/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal findMeanEmployeeCount() {
        return accountService.findMeanEmployeeCount();
    }

    @GetMapping("/stats/max/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findMaxEmployeeCount() {
        return accountService.findMaxEmployeeCount();
    }

    @GetMapping("/stats/min/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findMinEmployeeCount() {
        return accountService.findMinEmployeeCount();
    }

    @GetMapping("/stats/median/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public double findMedianEmployeeCount() {
        return accountService.findMedianEmployeeCount();
    }


}