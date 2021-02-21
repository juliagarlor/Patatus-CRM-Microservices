package com.ironhack.opportunitiesservice.repository;

import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ironhack.opportunitiesservice.enums.*;
import com.ironhack.opportunitiesservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.math.*;
import java.util.*;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    //method to get an opportunity list with an specific sales rep
    List<Opportunity> findByRepOpportunityId(Long id);
    //method to get an opportunity list with an specific sales rep and an specific status
    List<Opportunity> findByRepOpportunityIdAndStatus(Long id, Status status);
    //method to get an opportunity by accountId
    Optional<Opportunity> findByAccountId(Long id);




    // STATS AND STATS AND MORE STATS! LOVE STATS? I LOVE STATS! WE WANT STATS!


    //A count of all Opportunities by SalesRep
    @Query("SELECT o.repOpportunityId, COUNT(o) FROM Opportunity o GROUP BY o")
    List<Object[]> findOpportunityCountBySalesRep();

    //A count of Opportunities by Status and Sales Rep
    @Query("SELECT o.repOpportunityId, COUNT(o) FROM Opportunity o WHERE status = :status GROUP BY o")
    List<Object[]> findOpportunityByStatusCountBySalesRep(@Param("status") Enum status);

    // Ordered opportunities by account for the median
    @Query(value="SELECT oo.count FROM (SELECT COUNT(o.account_id) FROM opportunity o GROUP BY a.id) AS oo ORDER BY count", nativeQuery = true)
    List<Integer[]> findOpportunitiesByAccountOrdered();

    // Max opportunities by account
    @Query(value="SELECT o.account_id ,COUNT(o) FROM opportunity o GROUP BY a.id ORDER BY count DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxOpportunitiesByAccount();

    // Min opportunities by account
    @Query(value="SELECT o.account_id ,COUNT(o) FROM opportunity o GROUP BY a.id ORDER BY count ASC LIMIT 1", nativeQuery = true)
            List<Object[]> findMinOpportunitiesByAccount();

            // Average opportunities by account
            @Query(value="SELECT AVG(oo.count) FROM (SELECT COUNT(o) AS count FROM opportunity o GROUP BY o.id) AS oo", nativeQuery = true)
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
            @Query("SELECT o.quantity FROM Opportunity o ORDER BY o.quantity")
            List<Integer[]> orderOpportunities();




}
