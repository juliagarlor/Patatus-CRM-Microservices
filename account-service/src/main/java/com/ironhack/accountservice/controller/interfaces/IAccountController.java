package com.ironhack.accountservice.controller.interfaces;

import com.ironhack.accountservice.dto.AccountDTO;
import com.ironhack.accountservice.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountController {

    Account postAccount(AccountDTO accountDTO);
    List<Account> getAllAccounts();
    Account getAccountById(Long id);

    // Routes for the stats:

    BigDecimal findMeanEmployeeCount();
    List<Object[]> findMaxEmployeeCount();
    List<Object[]> findMinEmployeeCount();
    List<Integer[]> findEmployeesByAccountOrdered();

}
