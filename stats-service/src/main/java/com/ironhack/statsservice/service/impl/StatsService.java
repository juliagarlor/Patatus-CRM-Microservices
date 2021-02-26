package com.ironhack.statsservice.service.impl;

import com.ironhack.statsservice.clients.*;
import com.ironhack.statsservice.enums.*;
import com.ironhack.statsservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cloud.circuitbreaker.resilience4j.*;
import org.springframework.cloud.client.circuitbreaker.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

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

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public String findLeadCountBySalesRep() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("leadService-dev");
        return leadCircuitBreaker.run(() -> leadClient.findLeadCountBySalesRep(), throwable -> stringFallback("Lead"));
    }

    public String findOpportunityCountBySalesRep() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityCountBySalesRep(), throwable -> stringFallback("Opportunity"));
    }

    public String findOpportunityByStatusCountBySalesRep(String status) {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityByStatusCountBySalesRep(status), throwable -> stringFallback("Opportunity"));
    }

    public String findOpportunityCountByCountry() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityCountByCountry(), throwable -> stringFallback("Opportunity"));
    }

    public String findOpportunityByStatusCountByCountry(String status) {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityByStatusCountByCountry(status), throwable -> stringFallback("Opportunity"));
    }

    public String findOpportunityCountByCity() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityCountByCity(), throwable -> stringFallback("Opportunity"));
    }

    public String findOpportunityByStatusCountByCity(String status) {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityByStatusCountByCity(status), throwable -> stringFallback("Opportunity"));
    }

    public String findOpportunityCountByIndustry() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityCountByIndustry(), throwable -> stringFallback("Opportunity"));
    }

    public String findOpportunityByStatusCountByIndustry(String status) {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.findOpportunityByStatusCountByIndustry(status), throwable -> stringFallback("Opportunity"));
    }

    public BigDecimal findMeanEmployeeCount() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("accountService-dev");
        return leadCircuitBreaker.run(() -> accountClient.findMeanEmployeeCount(), throwable -> bigDecimalFallback("Account"));
    }

    public double findMedianEmployeeCount() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("accountService-dev");
        return leadCircuitBreaker.run(() -> accountClient.findMedianEmployeeCount(), throwable -> doubleFallback("Account"));
    }

    public List<Object[]> findMaxEmployeeCount() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("accountService-dev");
        return leadCircuitBreaker.run(() -> accountClient.findMaxEmployeeCount(), throwable -> listFallback("Account"));
    }

    public List<Object[]> findMinEmployeeCount() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("accountService-dev");
        return leadCircuitBreaker.run(() -> accountClient.findMinEmployeeCount(), throwable -> listFallback("Account"));
    }

    public BigDecimal getMeanOpportunities(String data) {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.getMeanOpportunities(data), throwable -> bigDecimalFallback("Opportunity"));
    }

    public double getMedian(String data) {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.getMedian(data), throwable -> doubleFallback("Opportunity"));
    }

    public int getMaxQuantity() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.getMaxQuantity(), throwable -> opportunityIntFallback());
    }

    public int getMinQuantity() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.getMinQuantity(), throwable -> opportunityIntFallback());
    }

    public List<Object[]> getMaxOpportunities() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.getMaxOpportunities(), throwable -> listFallback("Opportunity"));
    }

    public List<Object[]> getMinOpportunities() {
        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");
        return leadCircuitBreaker.run(() -> opportunityClient.getMinOpportunities(), throwable -> listFallback("Opportunity"));
    }

    public String stringFallback(String service){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, service + " Service is down...");
    }

    public BigDecimal bigDecimalFallback(String service){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, service + " Service is down...");
    }
    public double doubleFallback(String service){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, service + " Service is down...");
    }
    public int opportunityIntFallback(){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Opportunity Service is down...");
    }
    public List<Object[]> listFallback(String service){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, service + " Service is down...");
    }
}
