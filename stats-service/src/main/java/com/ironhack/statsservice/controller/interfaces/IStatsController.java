package com.ironhack.statsservice.controller.interfaces;

import com.ironhack.statsservice.enums.*;

import java.math.*;
import java.util.*;

public interface IStatsController {
    String findLeadCountBySalesRep();
    String findOpportunityCountBySalesRep();
    String findOpportunityByStatusCountBySalesRep(Status status);
    String findOpportunityCountByCountry();
    String findOpportunityByStatusCountByCountry(Status status);
    String findOpportunityCountByCity();
    String findOpportunityByStatusCountByCity(Status status);
    String findOpportunityCountByIndustry();
    String findOpportunityByStatusCountByIndustry(Status status);
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
