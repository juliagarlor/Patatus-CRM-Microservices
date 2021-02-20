package com.ironhack.opportunitiesservice.service.impl;

import com.ironhack.opportunitiesservice.client.AccountClient;
import com.ironhack.opportunitiesservice.controller.dto.*;
import com.ironhack.opportunitiesservice.enums.Industry;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import com.ironhack.opportunitiesservice.repository.OpportunityRepository;
import com.ironhack.opportunitiesservice.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpportunityService implements IOpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private AccountClient accountClient;


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

    public OpportunityDTO getOpportunityById(Long id) {

        if(opportunityRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "opportunity with id " +id + " not found");
        }
        Opportunity opportunity = opportunityRepository.findById(id).get();

        return new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(), opportunity.getAccountId());

    }

    public List<OpportunityDTO> getOpportunitiesBySalesRep(Long salesRepId) {

        if(opportunityRepository.findByRepOpportunityId(salesRepId).get(0) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Any opportunity with a sales rep with id " + salesRepId + " was found");
        }
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Opportunity opportunity: opportunityRepository.findByRepOpportunityId(salesRepId)){
            OpportunityDTO  opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(),
                                            opportunity.getDecisionMakerId(), opportunity.getStatus(),
                                            opportunity.getProduct(), opportunity.getRepOpportunityId(),
                                            opportunity.getAccountId());
            opportunityDTOList.add(opportunityDTO);
        }
        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(Long salesRepId, Status status) {
        if(opportunityRepository.findByRepOpportunityId(salesRepId).get(0) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Any opportunity with a sales rep with id " + salesRepId + " was found");
        }
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Opportunity opportunity: opportunityRepository.findByRepOpportunityIdAndStatus(salesRepId, status)){
            OpportunityDTO  opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(),
                    opportunity.getDecisionMakerId(), opportunity.getStatus(),
                    opportunity.getProduct(), opportunity.getRepOpportunityId(),
                    opportunity.getAccountId());
            opportunityDTOList.add(opportunityDTO);
        }
        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesByCountry(String country) {

        //insert a list with the accounts by countries
        List<Long> countryDTOList = accountClient.getAccountByCountry(country);

        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: countryDTOList){
            if(opportunityRepository.findByAccountId(accountId).isPresent()){
                Opportunity opportunity = opportunityRepository.findByAccountId(accountId).get();
                OpportunityDTO opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(),opportunity.getAccountId());
                opportunityDTOList.add(opportunityDTO);
            }
        }

        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesByCountryAndStatus(String country, Status status) {

        //insert a list with the accounts by countries
        List<Long> countryDTOList = accountClient.getAccountByCountry(country);

        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: countryDTOList){
            if(opportunityRepository.findByAccountId(accountId).get().getStatus().equals(status)){
                Opportunity opportunity = opportunityRepository.findByAccountId(accountId).get();
                OpportunityDTO opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(),opportunity.getAccountId());
                opportunityDTOList.add(opportunityDTO);
            }
        }

        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesByCity(String city) {
        //insert a list with the accounts by countries
        List<Long> cityDTOList = accountClient.getAccountByCity(city);

        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: cityDTOList){
            if(opportunityRepository.findByAccountId(accountId).isPresent()){
                Opportunity opportunity = opportunityRepository.findByAccountId(accountId).get();
                OpportunityDTO opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(),opportunity.getAccountId());
                opportunityDTOList.add(opportunityDTO);
            }
        }

        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesByCityAndStatus(String city, Status status) {
        //insert a list with the accounts by countries
        List<Long> cityDTOList = accountClient.getAccountByCity(city);

        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: cityDTOList){
            if(opportunityRepository.findByAccountId(accountId).get().getStatus().equals(status)){
                Opportunity opportunity = opportunityRepository.findByAccountId(accountId).get();
                OpportunityDTO opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(),opportunity.getAccountId());
                opportunityDTOList.add(opportunityDTO);
            }
        }

        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesByIndustry(Industry industry) {
        //insert a list with the accounts by countries
        List<Long> industryDTOList = accountClient.getAccountByIndustry(industry);

        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: industryDTOList){
            if(opportunityRepository.findByAccountId(accountId).isPresent()){
                Opportunity opportunity = opportunityRepository.findByAccountId(accountId).get();
                OpportunityDTO opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(),opportunity.getAccountId());
                opportunityDTOList.add(opportunityDTO);
            }
        }

        return opportunityDTOList;
    }

    public List<OpportunityDTO> getOpportunitiesByIndustryAndStatus(Industry industry, Status status) {
        //insert a list with the accounts by countries
        List<Long> industryDTOList = accountClient.getAccountByIndustry(industry);

        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: industryDTOList){
            if(opportunityRepository.findByAccountId(accountId).get().getStatus().equals(status)){
                Opportunity opportunity = opportunityRepository.findByAccountId(accountId).get();
                OpportunityDTO opportunityDTO = new OpportunityDTO(opportunity.getId(), opportunity.getQuantity(), opportunity.getDecisionMakerId(), opportunity.getStatus(), opportunity.getProduct(), opportunity.getRepOpportunityId(),opportunity.getAccountId());
                opportunityDTOList.add(opportunityDTO);
            }
        }

        return opportunityDTOList;
    }

    //===========================================
    //Post methods
    //===========================================

    public Opportunity createOpportunity(OpportunityDTO opportunityDTO) {

        Opportunity opportunity = new Opportunity( opportunityDTO.getQuantity(), opportunityDTO.getDecisionMakerId(),
                opportunityDTO.getStatus(), opportunityDTO.getProduct(), opportunityDTO.getRepOpportunityId(), opportunityDTO.getAccountId());


        return opportunityRepository.save(opportunity);
    }

    //===========================================
    //Patch methods
    //===========================================

    public void updateOpportunityStatus(Long id, OpportunityStatusDTO opportunityStatusDTO) {
        if(opportunityRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "opportunity with id " +id + " not found");
        }

        Opportunity opportunity =  opportunityRepository.findById(id).get();
        opportunity.setStatus(opportunityStatusDTO.getStatus());
        opportunityRepository.save(opportunity);

    }

    public void updateOpportunityAccountId(Long id, AccountIdDTO accountIdDTO) {
        if(opportunityRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "opportunity with id " +id + " not found");
        }

        Opportunity opportunity =  opportunityRepository.findById(id).get();
        opportunity.setAccountId(accountIdDTO.getId());
        opportunityRepository.save(opportunity);
    }
}
