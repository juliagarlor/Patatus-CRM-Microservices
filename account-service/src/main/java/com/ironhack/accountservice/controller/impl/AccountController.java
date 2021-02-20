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

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account postAccount(@RequestBody @Valid AccountDTO accountDTO) {
        return accountService.postAccount(accountDTO);
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable() Long id) {
        return accountService.getAccountById(id);
    }

    // Routes for stats:

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