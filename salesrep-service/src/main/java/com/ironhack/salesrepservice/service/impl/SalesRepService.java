package com.ironhack.salesrepservice.service.impl;

import com.ironhack.salesrepservice.Repository.SalesRepRepository;
import com.ironhack.salesrepservice.client.OpportunityClient;
import com.ironhack.salesrepservice.controller.dto.LeadDTO;
import com.ironhack.salesrepservice.controller.dto.OpportunityDTO;
import com.ironhack.salesrepservice.controller.dto.SalesRepDTO;
import com.ironhack.salesrepservice.enums.Status;
import com.ironhack.salesrepservice.model.SalesRep;
import com.ironhack.salesrepservice.service.interfaces.ISalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesRepService implements ISalesRepService {

    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private com.ironhack.salesrepservice.client.LeadClient leadClient;
    @Autowired
    private OpportunityClient opportunityClient;

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    //===========================================
    //Get methods
    //===========================================

    public List<SalesRepDTO> findAll() {

        List<SalesRepDTO> salesRepDTOS = new ArrayList<SalesRepDTO>();

        for(SalesRep salesRep: salesRepRepository.findAll()){
            SalesRepDTO salesRepDTO = new SalesRepDTO(salesRep.getId(), salesRep.getName());
            salesRepDTOS.add(salesRepDTO);
        }

        return salesRepDTOS;
    }

    @Override
    public Long getSalesRepId(Long id) {
        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }
        return id;
    }

    public List<LeadDTO> getLeadsBySalesRepId(Long id) {

        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("leadService-dev");

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        List<LeadDTO> leadDTOList = leadCircuitBreaker.run(() -> leadClient.getAllLeads(), throwable -> leadFallback());
        SalesRep salesRep = salesRepRepository.findById(id).get();

        List<LeadDTO> newLeadDTOList = new ArrayList<>();

        for(LeadDTO leadDTO: leadDTOList){
            if (salesRep.getId() ==(leadDTO.getSalesRepId())){
                newLeadDTOList.add(leadDTO);
            }
        }
        return newLeadDTOList;
    }



    public Integer getCountOfLeadsBySalesRepId(Long id){

        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("leadService-dev");

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        List<LeadDTO> leadDTOList = leadCircuitBreaker.run(() -> leadClient.getAllLeads(), throwable -> leadFallback());
        SalesRep salesRep = salesRepRepository.findById(id).get();

        Integer count = 0;

        for(LeadDTO leadDTO: leadDTOList){
            if (salesRep.getId() ==(leadDTO.getSalesRepId())){
                count += 1;
            }
        }
        return count;
    }

    public List<OpportunityDTO> getOpportunitiesBySalesRepId(Long id) {

        CircuitBreaker opportunityCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        return opportunityCircuitBreaker.run(() -> opportunityClient.getOpportunitiesBySalesRep(id), throwable -> opportunityFallback());
    }



    public Integer getCountOfOpportunitiesBySalesRepId(Long id){

        CircuitBreaker opportunityCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        Integer count = 0;

        for (OpportunityDTO opportunityDTO: opportunityCircuitBreaker.run(() -> opportunityClient.getOpportunitiesBySalesRep(id), throwable -> opportunityFallback())){
            count+= 1;
        }

        return count;
    }

    public List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(Long id, String status) {

        CircuitBreaker opportunityCircuitBreaker = circuitBreakerFactory.create("opportunityService-dev");

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }
        try {
            Status.valueOf(status.toUpperCase());  // Validation of Status enum.
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(status + " is not a valid Status.");
        }

        return opportunityCircuitBreaker.run(() -> opportunityClient.getOpportunitiesBySalesRepAndStatus(id, status), throwable -> opportunityFallback());
    }

    //=========================================================
    //Post Methods
    //=========================================================

    public SalesRep createSalesRep(SalesRepDTO salesRepDTO) {

        SalesRep salesRep = new SalesRep(salesRepDTO.getName());
        salesRepRepository.save(salesRep);

        return salesRep;
    }


    //=========================================================
    //Fallback Methods
    //=========================================================

    private List<LeadDTO> leadFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Lead Service is down...");
    }
    private List<OpportunityDTO> opportunityFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Opportunity Service is down...");
    }

}
