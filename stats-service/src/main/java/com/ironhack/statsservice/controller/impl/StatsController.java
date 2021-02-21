package com.ironhack.statsservice.controller.impl;

import com.ironhack.statsservice.controller.interfaces.*;
import com.ironhack.statsservice.enums.*;
import com.ironhack.statsservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

@RestController
public class StatsController implements IStatsController {

    @Autowired
    private IStatsService iStatsService;

//    leads by salesrep
    @GetMapping("/leads/count/bysalesrep")
    @ResponseStatus(HttpStatus.OK)
    public String findLeadCountBySalesRep(){
        return iStatsService.findLeadCountBySalesRep();
    }

//    opportunities by salesrep
    @GetMapping("/opportunities/count/by-salesRep")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountBySalesRep() {
        return iStatsService.findOpportunityCountBySalesRep();
    }

//    opportunities by salesrep and status
    @GetMapping("/opportunities/count/by-salesRep/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountBySalesRep(@PathVariable Status status) {
        return iStatsService.findOpportunityByStatusCountBySalesRep(status);
    }

//    opportunities by country
    @GetMapping("/opportunities/count/by-country")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountByCountry() {
        return iStatsService.findOpportunityCountByCountry();
    }

//    opportunities by country and status
    @GetMapping("/opportunities/count/by-country/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountByCountry(@PathVariable Status status) {
        return iStatsService.findOpportunityByStatusCountByCountry(status);
    }

//    opportunities by city
    @GetMapping("/opportunities/count/by-city/{city}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountByCity() {
        return iStatsService.findOpportunityCountByCity();
    }

//    opportunities by city and status
    @GetMapping("/opportunities/count/by-city/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountByCity(@PathVariable Status status) {
        return iStatsService.findOpportunityByStatusCountByCity(status);
    }

//    opportunities by industry
    @GetMapping("/opportunities/count/by-industry")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountByIndustry() {
        return iStatsService.findOpportunityCountByIndustry();
    }

//    opportunities by industry and status
    @GetMapping("/opportunities/count/by-industry/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountByIndustry(@PathVariable Status status) {
        return iStatsService.findOpportunityByStatusCountByIndustry(status);
    }

//    mean employee count
    @GetMapping("/stats/mean/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal findMeanEmployeeCount() {
        return iStatsService.findMeanEmployeeCount();
    }

//    median employee count
    @GetMapping("/stats/median/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public double findMedianEmployeeCount() {
        return iStatsService.findMedianEmployeeCount();
    }

//    max employee count
    @GetMapping("/stats/max/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findMaxEmployeeCount() {
        return iStatsService.findMaxEmployeeCount();
    }

//    min employee count
    @GetMapping("/stats/min/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findMinEmployeeCount() {
        return iStatsService.findMinEmployeeCount();
    }

//    mean quantity or opportunities per account
    @GetMapping("/stats/mean/{data}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getMeanOpportunities(@PathVariable String data) {
        return iStatsService.getMeanOpportunities(data);
    }

//    median quantity or opportunities per account
    @GetMapping("/stats/median/{data}")
    @ResponseStatus(HttpStatus.OK)
    public double getMedian(@PathVariable String data) {
        return iStatsService.getMedian(data);
    }

//    max quantity
    @GetMapping("/stats/max/quantity")
    @ResponseStatus(HttpStatus.OK)
    public int getMaxQuantity() {
        return iStatsService.getMaxQuantity();
    }

//    min quantity
    @GetMapping("/stats/min/quantity")
    @ResponseStatus(HttpStatus.OK)
    public int getMinQuantity() {
        return iStatsService.getMinQuantity();
    }

//    max opportunities per account
    @GetMapping("/stats/max/opportunities")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> getMaxOpportunities() {
        return iStatsService.getMaxOpportunities();
    }

//    min opportunities per account
    @GetMapping("/stats/min/opportunities")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> getMinOpportunities() {
        return iStatsService.getMinOpportunities();
    }


}
