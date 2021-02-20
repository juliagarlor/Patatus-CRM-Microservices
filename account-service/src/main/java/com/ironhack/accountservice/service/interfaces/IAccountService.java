package com.ironhack.accountservice.service.interfaces;

import com.ironhack.accountservice.dto.AccountDTO;
import com.ironhack.accountservice.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    Account postAccount(AccountDTO accountDTO);
    List<Account> getAllAccounts();
    Account getAccountById(Long id);

    List<Long> getAccountsByCountry(String country);
    List<Long> getAccountsByCity(String city);
    List<Long> getAccountsByIndustry(String industry);

    // Routes for the stats:

    BigDecimal findMeanEmployeeCount();
    List<Object[]> findMaxEmployeeCount();
    List<Object[]> findMinEmployeeCount();
//    List<Integer[]> findEmployeesByAccountOrdered();

    double findMedianEmployeeCount();
}