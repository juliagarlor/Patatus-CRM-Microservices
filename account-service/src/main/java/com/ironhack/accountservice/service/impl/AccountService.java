package com.ironhack.accountservice.service.impl;

import com.ironhack.accountservice.dto.AccountDTO;
import com.ironhack.accountservice.enums.Industry;
import com.ironhack.accountservice.model.Account;
import com.ironhack.accountservice.repository.AccountRepository;
import com.ironhack.accountservice.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account postAccount(AccountDTO accountDTO) {
        Account account = new Account();
        try {
            account.setIndustry(Industry.valueOf(accountDTO.getIndustry().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(accountDTO.getIndustry() + " is not a valid Industry.");
        }
        account.setEmployeeCount(accountDTO.getEmployeeCount());
        account.setCity(accountDTO.getCity());
        account.setCountry(accountDTO.getCountry());

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no account whit id: " + id);
        }
    }

    @Override
    public List<Long> getAccountsByCountry(String country) {
        return accountRepository.getAccountsByCountry(country);
    }

    @Override
    public List<Long> getAccountsByCity(String city) {
        return accountRepository.getAccountsByCity(city);
    }


    public List<Long> getAccountsByIndustry(String industry) {
        return accountRepository.getAccountsByIndustry(industry);
    }

    @Override
    public List<String> getCities() {
        return accountRepository.getAllCities();
    }

    @Override
    public List<String> getCountries() {
        return accountRepository.getAllCountries();
    }

    public BigDecimal findMeanEmployeeCount() {
        BigDecimal meanEmployeeCount = accountRepository.findMeanEmployeeCount();
        return meanEmployeeCount == null ? BigDecimal.ZERO : meanEmployeeCount;
    }

    public List<Object[]> findMaxEmployeeCount() {
        List<Object[]> maxEmployeeCount = accountRepository.findMaxEmployeeCount();
        if (maxEmployeeCount.size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no accounts yet.");
        }
        return maxEmployeeCount;
    }

    public List<Object[]> findMinEmployeeCount() {
        List<Object[]> minEmployeeCount = accountRepository.findMinEmployeeCount();
        if (minEmployeeCount.size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no accounts yet.");
        }
        return minEmployeeCount;
    }

//    public List<Integer[]> findEmployeesByAccountOrdered() {
//        List<Integer[]> employeesByAccountOrdered = accountRepository.findEmployeesByAccountOrdered();
//        if (employeesByAccountOrdered.size() == 0){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no accounts yet.");
//        }
//        return employeesByAccountOrdered;
//    }

    public double findMedianEmployeeCount() {
        List<Integer[]> employeesByAccountOrdered = accountRepository.findEmployeesByAccountOrdered();

        if (employeesByAccountOrdered.size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no accounts yet.");
        }
        return findMedian(employeesByAccountOrdered);
    }

    public double findMedian(List<Integer[]> objects){
        double median;
        int medianPosition = objects.size()/2;
        if(objects.size() % 2 != 0 ){
            median = objects.get(medianPosition)[0];
        } else {
            double firstHalf = (double) objects.get((objects.size()/2)-1)[0];
            double secondHalf = (double) objects.get(medianPosition)[0];
            median = (firstHalf + secondHalf)/2;
        }
        return median;
    }
}