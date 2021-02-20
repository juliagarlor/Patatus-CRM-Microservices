package com.ironhack.opportunitiesservice.controller.impl;

import com.ironhack.opportunitiesservice.controller.dto.AccountIdDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityStatusDTO;
import com.ironhack.opportunitiesservice.controller.interfaces.IOpportunityController;
import com.ironhack.opportunitiesservice.enums.Industry;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import com.ironhack.opportunitiesservice.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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
    private IOpportunityService opportunityService;

    //===========================================
    //Get methods
    //===========================================

    //this big method, play with different combinations of params to get different info. for example, opportunities
    //By country and status
    @GetMapping("/opportunities")
    @ResponseStatus(HttpStatus.OK)
    public List<OpportunityDTO> getOpportunitiesBy(@RequestParam(name = "salesrep-id") Optional<Integer> salesRepId,
                                                   @RequestParam(name = "status") Optional<Status> status,
                                                   @RequestParam(name = "country") Optional<String> country,
                                                   @RequestParam(name = "city") Optional<String> city,
                                                   @RequestParam(name = "industry") Optional<Industry> industry) {
        if(salesRepId.isEmpty() && status.isEmpty() && country.isEmpty() && city.isEmpty() && industry.isEmpty()){
            return opportunityService.getAllOpportunities();
        }else if(salesRepId.isPresent() && status.isEmpty() && country.isEmpty() && city.isEmpty() && industry.isEmpty()){
            return opportunityService.getOpportunitiesBySalesRep(salesRepId.get());
        }else if(salesRepId.isEmpty() && status.isEmpty() && country.isPresent() && city.isEmpty() && industry.isEmpty()){
            return opportunityService.getOpportunitiesByCountry(country.get());
        }else if(salesRepId.isEmpty() && status.isPresent() && country.isPresent() && city.isEmpty() && industry.isEmpty()){
            return opportunityService.getOpportunitiesByCountryAndStatus(country.get(),status.get());
        }else if(salesRepId.isEmpty() && status.isEmpty() && country.isEmpty() && city.isPresent() && industry.isEmpty()){
            return opportunityService.getOpportunitiesByCity(city.get());
        }else if(salesRepId.isEmpty() && status.isPresent() && country.isEmpty() && city.isPresent() && industry.isEmpty()){
            return opportunityService.getOpportunitiesByCityAndStatus(city.get(),status.get());
        }else if(salesRepId.isEmpty() && status.isEmpty() && country.isEmpty() && city.isEmpty() && industry.isPresent()){
            return opportunityService.getOpportunitiesByIndustry(industry.get());
        }else if(salesRepId.isEmpty() && status.isPresent() && country.isEmpty() && city.isEmpty() && industry.isPresent()){
            return opportunityService.getOpportunitiesByIndustryAndStatus(industry.get(), status.get());
        }else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "couldn't get any info from database");
        }
    }

    @GetMapping("/opportunity/{id}")
    public OpportunityDTO getOpportunityDTOById(@PathVariable int id) {
        return opportunityService.getOpportunityById(id);
    }

    //===========================================
    //Post methods
    //===========================================

    @PostMapping("/opportunity")
    @ResponseStatus(HttpStatus.CREATED)
    public Opportunity createOpportunity(OpportunityDTO opportunityDTO) {
        return opportunityService.createOpportunity(opportunityDTO);
    }

    //===========================================
    //Post methods
    //===========================================

    //TODO: juntar las dos rutas en una
    @PatchMapping("/opportunity/{opportunityId}/status")
     public void updateOpportunityStatus(@PathVariable int opportunityId, @RequestBody OpportunityStatusDTO opportunityStatusDTO) {
        opportunityService.updateOpportunityStatus(opportunityId, opportunityStatusDTO);
    }

    @PatchMapping("/opportunity/{opportunityId}/account-id")
     public void updateOpportunityAccountId(@PathVariable int opportunityId,@RequestBody AccountIdDTO accountIdDTO) {
        opportunityService.updateOpportunityAccountId(opportunityId, accountIdDTO);
     }
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
