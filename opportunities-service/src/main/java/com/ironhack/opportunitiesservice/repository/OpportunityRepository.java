package com.ironhack.opportunitiesservice.repository;

import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.*;
import java.util.*;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    // Ordered opportunities by account for the median
    @Query(value="SELECT o.count FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo ORDER BY count", nativeQuery = true)
    List<Integer[]> findOpportunitiesByAccountOrdered();

    // Max opportunities by account
    @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxOpportunitiesByAccount();

    // Min opportunities by account
    @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count ASC LIMIT 1", nativeQuery = true)
    List<Object[]> findMinOpportunitiesByAccount();

    // Average opportunities by account
    @Query(value="SELECT AVG(oo.count) FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo", nativeQuery = true)
    BigDecimal findAvgOpportunitiesByAccount();

//    Average quantity:
    @Query("SELECT AVG(o.quantity) FROM Opportunity o")
    BigDecimal findAverageQuantityFromOpportunities();

//    Min quantity
    @Query("SELECT MIN(o.quantity) FROM Opportunity o")
    Integer findMinQuantityFromOpportunities();

//    Max quantity
    @Query("SELECT MAX(o.quantity) FROM Opportunity o")
    Integer findMaxQuantityFromOpportunities();

//    Median quantity
    @Query ("SELECT o.quantity FROM Opportunity o ORDER BY o.quantity")
    List<Integer[]> orderOpportunities();

}
