package com.ironhack.accountservice.controller.interfaces;

import com.ironhack.accountservice.dto.AccountDTO;
import com.ironhack.accountservice.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountController {

    AccountDTO postAccount(AccountDTO accountDTO);
    List<AccountDTO> getAllAccounts();
    Account getAccountById(Long id);

    List<Long> getAccountsByCountry(String country);
    List<Long> getAccountsByCity(String city);
    List<Long> getAccountsByIndustry(String industry);

    void deleteAccounts();

    // Routes for the stats:

    List<String> getCities();
    List<String> getCountries();

    BigDecimal findMeanEmployeeCount();
    List<Object[]> findMaxEmployeeCount();
    List<Object[]> findMinEmployeeCount();

    double findMedianEmployeeCount();


}