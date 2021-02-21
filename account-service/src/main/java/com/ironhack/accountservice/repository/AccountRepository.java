package com.ironhack.accountservice.repository;

import com.ironhack.accountservice.enums.Industry;
import com.ironhack.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    // Account count
    @Query("SELECT COUNT(id) FROM Account")
    Integer countAccount();

    @Query("SELECT id FROM Account WHERE country = :country")
    List<Long> getAccountsByCountry(@Param("country") String country);

    @Query("SELECT id FROM Account WHERE city = :city")
    List<Long> getAccountsByCity(@Param("city") String city);

    @Query("SELECT id FROM Account WHERE industry = :industry")
    List<Long> getAccountsByIndustry(@Param("industry") Enum industry);

    // Lists of cities and countries in String:
    @Query("SELECT city FROM Account GROUP BY city")
    List<String> getAllCities();

    @Query("SELECT country FROM Account GROUP BY country")
    List<String> getAllCountries();

    @Query("SELECT id FROM Account WHERE country = :country")
    List<Long> getAccountsByCountry(@Param("country") String country);

    @Query("SELECT id FROM Account WHERE city = :city")
    List<Long> getAccountsByCity(@Param("city") String city);

    @Query("SELECT id FROM Account WHERE industry = :industry")
    List<Long> getAccountsByIndustry(@Param("industry") Enum industry);


    // STATS:

    // The mean employeeCount
    @Query("SELECT AVG(employeeCount) FROM Account")
    BigDecimal findMeanEmployeeCount();

    // The maximum employeeCount
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxEmployeeCount();

    // The minimum employeeCount
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count ASC LIMIT 1", nativeQuery = true)
    List<Object[]> findMinEmployeeCount();

    // Ordered employee by account for the median
    @Query("SELECT a.employeeCount FROM Account a ORDER BY a.employeeCount")
    List<Integer[]> findEmployeesByAccountOrdered();
}