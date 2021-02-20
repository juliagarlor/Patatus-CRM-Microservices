package com.ironhack.accountservice.repository;

import com.ironhack.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // todo: esta query hay que pasarla a opportunity:
    // Account count
    @Query("SELECT COUNT(id) FROM Account")
    Integer countAccount();

    @Query("SELECT id FROM Account WHERE country = :country")
    List<Long> getAccountsByCountry(@Param("country") String country);

    @Query("SELECT id FROM Account WHERE city = :city")
    List<Long> getAccountsByCity(@Param("city") String city);

    @Query("SELECT id FROM Account WHERE industry = :industry")
    List<Long> getAccountsByIndustry(@Param("industry") String industry);

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

    // todo: estas queries hay que pasarlas a opportunity (y refactorizarlas claro):
        // Ordered opportunities by account for the median
        @Query(value="SELECT oo.count FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo ORDER BY count", nativeQuery = true)
        List<Integer[]> findOpportunitiesByAccountOrdered();

        // Max opportunities by account
        @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count DESC LIMIT 1", nativeQuery = true)
        List<Object[]> findMaxOpportunitiesByAccount();

        // Min opportunities by account
        @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count ASC LIMIT 1", nativeQuery = true)
        List<Object[]> findMinOpportunitiesByAccount();

        // Average opportunities by account
        @Query(value="SELECT AVG(oo.count) FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo", nativeQuery = true)
        double findAvgOpportunitiesByAccount();
}
