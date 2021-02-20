package com.ironhack.opportunitiesservice.service.impl;

import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityStatusDTO;
import com.ironhack.opportunitiesservice.enums.Product;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import com.ironhack.opportunitiesservice.repository.OpportunityRepository;
import com.ironhack.opportunitiesservice.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpportunityService implements IOpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;


    //===========================================
    //Get methods
    //===========================================
    public List<OpportunityDTO> getAllOpportunities() {

        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for (Opportunity opportunity: opportunityRepository.findAll()){
            OpportunityDTO opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(),
                    opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(),
                    opportunity.getRepOpportunityId(), opportunity.getAccountId());
            opportunityDTOList.add(opportunityDTO);
        }

        return opportunityDTOList;
    }

    public OpportunityDTO getOpportunityById(int id) {

        if(opportunityRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "opportunity with id " +id + " not found");
        }
        Opportunity opportunity = opportunityRepository.findById(id).get();

        return new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(), opportunity.getAccountId());

    }

    public List<OpportunityDTO> getOpportunitiesBySalesRep(int salesRepId) {

        if(opportunityRepository.findByRepOpportunityId(salesRepId).get(0) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Any opportunity with a sales rep with id " + salesRepId + " was found");
        }
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();
//
        for(Opportunity opportunity: opportunityRepository.findByRepOpportunityId(salesRepId)){
            OpportunityDTO  opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(),
                                            opportunity.getDecisionMakerId(), opportunity.getStatus(),
                                            opportunity.getProduct(), opportunity.getRepOpportunityId(),
                                            opportunity.getAccountId());

        }
        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(int salesRepId, Status status) {
        return null;
    }

    public List<OpportunityDTO> getOpportunitiesByCountry(String country) {
        return null;
    }

    public List<OpportunityDTO> getOpportunitiesByCountryAndStatus(String country, Status status) {
        return null;
    }

    public List<OpportunityDTO> getOpportunitiesByIndustry(String industry) {
        return null;
    }

    public List<OpportunityDTO> getOpportunitiesByIndustryAndStatus(String industry, Status status) {
        return null;
    }

    //===========================================
    //Post methods
    //===========================================

    public Opportunity createOpportunity(OpportunityDTO opportunityDTO) {
        return null;
    }

    //===========================================
    //Patch methods
    //===========================================

    public void updateOpportunityStatus(int id, OpportunityStatusDTO opportunityStatusDTO) {

    }
}
