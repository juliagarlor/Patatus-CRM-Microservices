package com.ironhack.opportunitiesservice.service.impl;

import com.ironhack.opportunitiesservice.client.*;
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
import java.math.*;
import java.util.*;

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

    public String findOpportunityCountByIndustry(){
        String output1 = "Produce: " + getOpportunitiesByIndustry(Industry.PRODUCE).size() + " opportunities";
        String output2 = "\ne-Commerce: " + getOpportunitiesByIndustry(Industry.ECOMMERCE).size() + " opportunities";
        String output3 = "\nManufacturing: " + getOpportunitiesByIndustry(Industry.MANUFACTURING).size() + " opportunities";
        String output4 = "\nMedical: " + getOpportunitiesByIndustry(Industry.MEDICAL).size() + " opportunities";
        String output5 = "\nOther: " + getOpportunitiesByIndustry(Industry.OTHER).size() + " opportunities";

        return output1 + output2 + output3 + output4 + output5;
    }

    public String findOpportunityByStatusCountByIndustry(Status status){
        String output1 = "Produce: " + getOpportunitiesByIndustryAndStatus(Industry.PRODUCE, status).size() + " opportunities";
        String output2 = "\ne-Commerce: " + getOpportunitiesByIndustryAndStatus(Industry.ECOMMERCE, status).size() + " opportunities";
        String output3 = "\nManufacturing: " + getOpportunitiesByIndustryAndStatus(Industry.MANUFACTURING, status).size() + " opportunities";
        String output4 = "\nMedical: " + getOpportunitiesByIndustryAndStatus(Industry.MEDICAL, status).size() + " opportunities";
        String output5 = "\nOther: " + getOpportunitiesByIndustryAndStatus(Industry.OTHER, status).size() + " opportunities";

        return output1 + output2 + output3 + output4 + output5;
    }

    public String findOpportunityCountByCity() {
        List<String> cities = accountClient.getCities();
        String output = "";
        for (String c : cities){
            output += "\n" + c + ": " + getOpportunitiesByCity(c).size() + " opportunities";
        }

        return output;
    }

    public String findOpportunityByStatusCountByCity(Status status) {
        List<String> cities = accountClient.getCities();
        String output = "";
        for (String c : cities){
            output += "\n" + c + ": " + getOpportunitiesByCityAndStatus(c, status).size() + " opportunities";
        }

        return output;
    }

    public String findOpportunityCountByCountry() {
        List<String> countries = accountClient.getCountries();
        String output = "";
        for (String c : countries){
            output += "\n" + c + ": " + getOpportunitiesByCountry(c).size() + " opportunities";
        }

        return output;
    }

    public String findOpportunityByStatusCountByCountry(Status status) {
        List<String> countries = accountClient.getCountries();
        String output = "";
        for (String c : countries){
            output += "\n" + c + ": " + getOpportunitiesByCountryAndStatus(c, status).size() + " opportunities";
        }

        return output;
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

    public BigDecimal getMean(String data) {
        switch (data.toLowerCase()){
            case "quantity":
                return opportunityRepository.findAverageQuantityFromOpportunities();
            case "opportunities":
                BigDecimal output = opportunityRepository.findAvgOpportunitiesByAccount();

                if (output == null){
                    return BigDecimal.ZERO;
                }
                return output;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "quantity or opportunities");
        }
    }

    public List<Object[]> getMaxOpportunities() {
        List<Object[]> output = opportunityRepository.findMaxOpportunitiesByAccount();

        if (output.size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
        }
        return output;
    }

    public int getMaxQuantity(){
        return opportunityRepository.findMaxQuantityFromOpportunities();
    }

    public List<Object[]> getMinOpportunities() {
        List<Object[]> output = opportunityRepository.findMinOpportunitiesByAccount();

        if (output.size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
        }
        return output;
    }

    public int getMinQuantity(){
        return opportunityRepository.findMinQuantityFromOpportunities();
    }

    public double getMedian(String data) {
        switch (data.toLowerCase()){
            case "quantity":
                List<Integer[]> orderedList = opportunityRepository.orderOpportunities();

                if (orderedList.size() == 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
                }

                return findMedian(orderedList);
            case "opportunities":
                orderedList = opportunityRepository.findOpportunitiesByAccountOrdered();

                if (orderedList.size() == 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
                }

                return findMedian(orderedList);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "quantity or opportunities");
        }
    }

    //===========================================
    //Post methods
    //===========================================

    public Opportunity createOpportunity(OpportunityDTO opportunityDTO) {
        // TODO: preguntar a Xabi cómo hacer lo de las cuentas.
        if (accountClient.getAccountById(opportunityDTO.getAccountId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The account with Id: " + opportunityDTO.getAccountId() + "doesn't exist.");
        }

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

        Opportunity opportunity = opportunityRepository.findById(id).get();
        opportunity.setAccountId(accountIdDTO.getId());
        opportunityRepository.save(opportunity);
    }

    // List MUST de previously ordered
    public double findMedian(List<Integer[]> objects){
        double median;
        int medianPosition = objects.size()/2;
        if(objects.size() % 2 != 0 ){
            median = objects.get(medianPosition)[0];
        } else {
            double firstHalf = (double) objects.get((objects.size()/2)-1)[0];
            double secondHalf = (double) objects.get(medianPosition)[0];
            median = (firstHalf + secondHalf)/2;
        }
        return median;
    }
}
