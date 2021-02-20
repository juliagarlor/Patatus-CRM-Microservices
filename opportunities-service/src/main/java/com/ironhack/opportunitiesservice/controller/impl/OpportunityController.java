package com.ironhack.opportunitiesservice.controller.impl;

import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.controller.interfaces.IOpportunityController;
import com.ironhack.opportunitiesservice.enums.Industry;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class OpportunityController implements IOpportunityController {

    @Autowired
    private IOpportunityService opportunityService;

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

        }

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "couldn't get any info from database");
    }

    @GetMapping("/opportunity/{id}")
    public OpportunityDTO getOpportunityDTOById(@PathVariable int id) {
        return opportunityService.getOpportunityById(id);
    }
}
