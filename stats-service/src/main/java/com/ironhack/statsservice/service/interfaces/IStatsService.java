package com.ironhack.statsservice.service.interfaces;

import com.ironhack.statsservice.enums.*;

import java.math.*;
import java.util.*;

public interface IStatsService {
    String findLeadCountBySalesRep();
    String findOpportunityCountBySalesRep();
    String findOpportunityByStatusCountBySalesRep(String status);
    String findOpportunityCountByCountry();
    String findOpportunityByStatusCountByCountry(String status);
    String findOpportunityCountByCity();
    String findOpportunityByStatusCountByCity(String status);
    String findOpportunityCountByIndustry();
    String findOpportunityByStatusCountByIndustry(String status);
    BigDecimal findMeanEmployeeCount();
    double findMedianEmployeeCount();
    List<Object[]> findMaxEmployeeCount();
    List<Object[]> findMinEmployeeCount();
    BigDecimal getMeanOpportunities(String data);
    double getMedian(String data);
    int getMaxQuantity();
    int getMinQuantity();
    List<Object[]> getMaxOpportunities();
    List<Object[]> getMinOpportunities();
}
