package com.ironhack.opportunitiesservice.controller.impl;

import com.ironhack.opportunitiesservice.controller.*;
import com.ironhack.opportunitiesservice.service.impl.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

@RestController
public class OpportunityController implements IOpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @GetMapping("/stats/mean/{data}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getMeanOpportunities(@PathVariable String data){
        return opportunityService.getMean(data);
    }

    @GetMapping("/stats/max/opportunities")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> getMaxOpportunities() {
        return opportunityService.getMaxOpportunities();
    }

    @GetMapping("/stats/max/quantity")
    @ResponseStatus(HttpStatus.OK)
    public int getMaxQuantity() {
        return opportunityService.getMaxQuantity();
    }

    @GetMapping("/stats/min/opportunities")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> getMinOpportunities() {
        return opportunityService.getMinOpportunities();
    }

    @GetMapping("/stats/min/quantity")
    @ResponseStatus(HttpStatus.OK)
    public int getMinQuantity() {
        return opportunityService.getMinQuantity();
    }

    @GetMapping("/stats/median/{data}")
    @ResponseStatus(HttpStatus.OK)
    public double getMedian(@PathVariable String data) {
        return opportunityService.getMedian(data);
    }
}
