package com.ironhack.opportunitiesservice.controller.impl;

import com.ironhack.opportunitiesservice.controller.dto.AccountIdDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityStatusDTO;
import com.ironhack.opportunitiesservice.controller.interfaces.IOpportunityController;
import com.ironhack.opportunitiesservice.model.Opportunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class OpportunityController implements IOpportunityController {

    @Autowired
    private IOpportunityController opportunityService;

    //===========================================
    //Get methods
    //===========================================

    //this big method, play with different combinations of params to get different info. for example, opportunities
    //By country and status
    @GetMapping("/opportunities")
    @ResponseStatus(HttpStatus.OK)
    public List<OpportunityDTO> getOpportunitiesBy(@RequestParam(name = "salesrep-id") Optional<Long> salesRepId,
                                                   @RequestParam(name = "status") Optional<String> status,
                                                   @RequestParam(name = "country") Optional<String> country,
                                                   @RequestParam(name = "city") Optional<String> city,
                                                   @RequestParam(name = "industry") Optional<String> industry) {
        if(salesRepId.isEmpty() && status.isEmpty() && country.isEmpty() && city.isEmpty() && industry.isEmpty()){
            return opportunityService.getAllOpportunities();
        }else if(salesRepId.isPresent() && status.isEmpty() && country.isEmpty() && city.isEmpty() && industry.isEmpty()){
            return opportunityService.getOpportunitiesBySalesRep(salesRepId.get());
        } else if(salesRepId.isPresent() && status.isPresent() && country.isEmpty() && city.isEmpty() && industry.isEmpty()){
            return opportunityService.getOpportunitiesBySalesRepAndStatus(salesRepId.get(), status.get());
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
    public OpportunityDTO getOpportunityDTOById(@PathVariable Long id) {
        return opportunityService.getOpportunityById(id);
    }

    //===========================================
    //Post methods
    //===========================================

    @PostMapping("/opportunity")
    @ResponseStatus(HttpStatus.CREATED)
    public Opportunity createOpportunity(@RequestBody OpportunityDTO opportunityDTO) {
        return opportunityService.createOpportunity(opportunityDTO);
    }

    //===========================================
    //Post methods
    //===========================================

    //TODO: juntar las dos rutas en una
    @PatchMapping("/opportunity/{opportunityId}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
     public void updateOpportunityStatus(@PathVariable Long opportunityId, @RequestBody OpportunityStatusDTO opportunityStatusDTO) {
        opportunityService.updateOpportunityStatus(opportunityId, opportunityStatusDTO);
    }

    @PatchMapping("/opportunity/{opportunityId}/account-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
     public void updateOpportunityAccountId(@PathVariable Long opportunityId,@RequestBody AccountIdDTO accountIdDTO) {
        opportunityService.updateOpportunityAccountId(opportunityId, accountIdDTO);
     }

    //===========================================
    //Stats routes
    //===========================================

    @GetMapping("/stats/mean/{data}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getMeanOpportunities(@PathVariable String data){
        return opportunityService.getMeanOpportunities(data);
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

    @GetMapping("/opportunities/count/by-salesRep")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountBySalesRep() {
        return opportunityService.findOpportunityCountBySalesRep();
    }

    @GetMapping("/opportunities/count/by-salesRep/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountBySalesRep(@PathVariable String status) {
        return opportunityService.findOpportunityByStatusCountBySalesRep(status);
    }

    @GetMapping("/opportunities/count/by-industry")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountByIndustry() {
        return opportunityService.findOpportunityCountByIndustry();
    }

    @GetMapping("/opportunities/count/by-industry/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountByIndustry(@PathVariable String status) {
        return opportunityService.findOpportunityByStatusCountByIndustry(status);
    }

    @GetMapping("/opportunities/count/by-city")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountByCity() {
        return opportunityService.findOpportunityCountByCity();
    }

    @GetMapping("/opportunities/count/by-city/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountByCity(@PathVariable String status) {
        return opportunityService.findOpportunityByStatusCountByCity(status);
    }

    @GetMapping("/opportunities/count/by-country")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityCountByCountry() {
        return opportunityService.findOpportunityCountByCountry();
    }

    @GetMapping("/opportunities/count/by-country/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String findOpportunityByStatusCountByCountry(@PathVariable String status) {
        return opportunityService.findOpportunityByStatusCountByCountry(status);
    }
}
