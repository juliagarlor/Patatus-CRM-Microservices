package com.ironhack.opportunitiesservice.repository;

import com.ironhack.opportunitiesservice.enums.*;
import com.ironhack.opportunitiesservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    //A count of all Opportunities by SalesRep
    @Query("SELECT o.repOpportunityId, COUNT(o) FROM Opportunity o GROUP BY o")
    List<Object[]> findOpportunityCountBySalesRep();

    //A count of Opportunities by Status and Sales Rep
    @Query("SELECT o.repOpportunityId, COUNT(o) FROM Opportunity o WHERE status = :status GROUP BY o")
    List<Object[]> findOpportunityByStatusCountBySalesRep(@Param("status") Enum status);


    // A count of all Opportunities by country
    @Query("SELECT a.country, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.country")
    List<Object[]> findOpportunitiesByCountry();

    // A count of all Opportunities by country and Status
    @Query("SELECT a.country, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.country")
    List<Object[]> findByCountryAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by city
    @Query("SELECT a.city, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.city")
    List<Object[]> findOpportunitiesByCity();

    // A count of all Opportunities by city and Status
    @Query("SELECT a.city, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.city")
    List<Object[]> findByCityAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by industry
    @Query("SELECT a.industry, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.industry")
    List<Object[]> findOpportunitiesByIndustry();

    // A count of all Opportunities by industry and Status
    @Query("SELECT a.industry, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.industry")
    List<Object[]> findByIndustryAndStatus(@Param("status") Enum status);



    // Ordered opportunities by account for the median
    @Query(value="SELECT o.count FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo ORDER BY count", nativeQuery = true)
    List<Integer[]> findOpportunitiesByAccountOrdered();

    // Max opportunities by account
    @Query(value="SELECT o.accountId ,COUNT(o) FROM opportunity o GROUP BY a.id ORDER BY count DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxOpportunitiesByAccount();

    // Min opportunities by account
    @Query(value="\"SELECT o.accountId ,COUNT(o) FROM opportunity o GROUP BY a.id ORDER BY count ASC LIMIT 1", nativeQuery = true)
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


    //method to get an opportunity list with an specific sales rep
    List<Opportunity> findByRepOpportunityId(int id);
    //method to get an opportunity list with an specific sales rep and an specific status
    List<Opportunity> findByRepOpportunityIdAndStatus(int id, Status status);
    //method to get an opportunity by accountId
    Optional<Opportunity> findByAccountId(int id);
}
