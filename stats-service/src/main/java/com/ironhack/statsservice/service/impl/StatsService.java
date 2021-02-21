package com.ironhack.statsservice.service.impl;

import com.ironhack.statsservice.clients.*;
import com.ironhack.statsservice.enums.*;
import com.ironhack.statsservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class StatsService implements IStatsService {
    @Autowired
    private AccountClient accountClient;

    @Autowired
    private LeadClient leadClient;

    @Autowired
    private OpportunityClient opportunityClient;

    public String findLeadCountBySalesRep() {
        return leadClient.findLeadCountBySalesRep();
    }

    public String findOpportunityCountBySalesRep() {
        return opportunityClient.findOpportunityCountBySalesRep();
    }

    public String findOpportunityByStatusCountBySalesRep(Status status) {
        return opportunityClient.findOpportunityByStatusCountBySalesRep(status);
    }

    public String findOpportunityCountByCountry() {
        return opportunityClient.findOpportunityCountByCountry();
    }

    public String findOpportunityByStatusCountByCountry(Status status) {
        return opportunityClient.findOpportunityByStatusCountByCountry(status);
    }

    public String findOpportunityCountByCity() {
        return opportunityClient.findOpportunityCountByCity();
    }

    public String findOpportunityByStatusCountByCity(Status status) {
        return opportunityClient.findOpportunityByStatusCountByCity(status);
    }

    public String findOpportunityCountByIndustry() {
        return opportunityClient.findOpportunityCountByIndustry();
    }

    public String findOpportunityByStatusCountByIndustry(Status status) {
        return opportunityClient.findOpportunityByStatusCountByIndustry(status);
    }

    public BigDecimal findMeanEmployeeCount() {
        return accountClient.findMeanEmployeeCount();
    }

    public double findMedianEmployeeCount() {
        return accountClient.findMedianEmployeeCount();
    }

    public List<Object[]> findMaxEmployeeCount() {
        return accountClient.findMaxEmployeeCount();
    }

    public List<Object[]> findMinEmployeeCount() {
        return accountClient.findMinEmployeeCount();
    }

    public BigDecimal getMeanOpportunities(String data) {
        return opportunityClient.getMeanOpportunities(data);
    }

    public double getMedian(String data) {
        return opportunityClient.getMedian(data);
    }

    public int getMaxQuantity() {
        return opportunityClient.getMaxQuantity();
    }

    public int getMinQuantity() {
        return opportunityClient.getMinQuantity();
    }

    public List<Object[]> getMaxOpportunities() {
        return null;
    }

    public List<Object[]> getMinOpportunities() {
        return null;
    }
}
