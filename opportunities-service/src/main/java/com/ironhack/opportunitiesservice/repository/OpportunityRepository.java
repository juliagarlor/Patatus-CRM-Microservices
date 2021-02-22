package com.ironhack.opportunitiesservice.repository;

import com.ironhack.opportunitiesservice.enums.*;
import com.ironhack.opportunitiesservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    //method to get an opportunity list with an specific sales rep
    List<Opportunity> findByRepOpportunityId(Long id);
    //method to get an opportunity list with an specific sales rep and an specific status
    List<Opportunity> findByRepOpportunityIdAndStatus(Long id, Status status);
    //method to get an opportunity by accountId
    List<Opportunity> findByAccountId(Long id);




    // STATS AND STATS AND MORE STATS! LOVE STATS? I LOVE STATS! WE WANT STATS!


    //A count of all Opportunities by SalesRep
    @Query("SELECT repOpportunityId, COUNT(id) FROM Opportunity GROUP BY repOpportunityId")
    List<Object[]> findOpportunityCountBySalesRep();

    //A count of Opportunities by Status and Sales Rep
    @Query("SELECT repOpportunityId, COUNT(id) FROM Opportunity WHERE status = :status GROUP BY repOpportunityId")
    List<Object[]> findOpportunityByStatusCountBySalesRep(@Param("status") Enum status);

    // Ordered opportunities by account for the median
    @Query(value="SELECT o.count FROM (SELECT COUNT(*) AS count FROM opportunity GROUP BY account_id) AS o ORDER BY count", nativeQuery = true)
    List<Integer[]> findOpportunitiesByAccountOrdered();

    // Max opportunities by account
    @Query(value="SELECT account_id ,COUNT(*) AS count FROM opportunity GROUP BY account_id ORDER BY count DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxOpportunitiesByAccount();

    // Min opportunities by account
    @Query(value="SELECT account_id ,COUNT(*) AS count FROM opportunity GROUP BY account_id ORDER BY count ASC LIMIT 1", nativeQuery = true)
            List<Object[]> findMinOpportunitiesByAccount();

    // Average opportunities by account
    @Query(value="SELECT AVG(o.count) FROM (SELECT COUNT(*) AS count FROM opportunity GROUP BY account_id) AS o", nativeQuery = true)
    BigDecimal findAvgOpportunitiesByAccount();

//    Average quantity:
            @Query("SELECT AVG(quantity) FROM Opportunity")
            BigDecimal findAverageQuantityFromOpportunities();

//    Min quantity
            @Query("SELECT MIN(quantity) FROM Opportunity")
            Integer findMinQuantityFromOpportunities();

//    Max quantity
            @Query("SELECT MAX(quantity) FROM Opportunity")
            Integer findMaxQuantityFromOpportunities();

//    Median quantity
            @Query("SELECT quantity FROM Opportunity ORDER BY quantity")
            List<Integer[]> orderOpportunities();




}
