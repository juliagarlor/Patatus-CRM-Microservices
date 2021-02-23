package com.ironhack.opportunitiesservice.service.impl;

import com.ironhack.opportunitiesservice.client.*;
import com.ironhack.opportunitiesservice.controller.dto.*;
import com.ironhack.opportunitiesservice.enums.*;
import com.ironhack.opportunitiesservice.model.*;
import com.ironhack.opportunitiesservice.repository.*;
import com.ironhack.opportunitiesservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

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

    public List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(Long salesRepId, String status) {

        Status status1 = checkValidStatus(status);

        if(opportunityRepository.findByRepOpportunityId(salesRepId).get(0) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Any opportunity with a sales rep with id " +
                    salesRepId + " was found");
        }
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Opportunity opportunity: opportunityRepository.findByRepOpportunityIdAndStatus(salesRepId, status1)){
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

        return returnByAccountID(countryDTOList);
    }

    public List<OpportunityDTO> getOpportunitiesByCountryAndStatus(String country, String status) {
        checkValidStatus(status);

        //insert a list with the accounts by countries
        List<Long> countryDTOList = accountClient.getAccountByCountry(country);

        return returnByAccountIDAndStatus(countryDTOList, status);
    }

    public List<OpportunityDTO> getOpportunitiesByCity(String city) {
        //insert a list with the accounts by countries
        List<Long> cityDTOList = accountClient.getAccountByCity(city);

        return returnByAccountID(cityDTOList);
    }

    public List<OpportunityDTO> getOpportunitiesByCityAndStatus(String city, String status) {
        checkValidStatus(status);
        //insert a list with the accounts by countries
        List<Long> cityDTOList = accountClient.getAccountByCity(city);

        return returnByAccountIDAndStatus(cityDTOList, status);
    }

    public List<OpportunityDTO> getOpportunitiesByIndustry(String industry) {
        checkValidIndustry(industry);
        //insert a list with the accounts by countries
        List<Long> industryDTOList = accountClient.getAccountByIndustry(industry);

        return returnByAccountID(industryDTOList);
    }

    public List<OpportunityDTO> getOpportunitiesByIndustryAndStatus(String industry, String status) {
        checkValidStatus(status);
        checkValidIndustry(industry);
        //insert a list with the accounts by countries
        List<Long> industryDTOList = accountClient.getAccountByIndustry(industry);

        return returnByAccountIDAndStatus(industryDTOList, status);
    }

    //===========================================
    //Post methods
    //===========================================

    public Opportunity createOpportunity(OpportunityDTO opportunityDTO) {

        checkValidStatus(opportunityDTO.getStatus().toString());
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
        checkValidStatus(opportunityStatusDTO.getStatus().toString());

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

    //===========================================
    //||              FOR STATS                ||
    //===========================================

    public String findOpportunityCountByIndustry(){
        String output1 = "Produce: " + getOpportunitiesByIndustry("PRODUCE").size() + " opportunities";
        String output2 = "\ne-Commerce: " + getOpportunitiesByIndustry("ECOMMERCE").size() + " opportunities";
        String output3 = "\nManufacturing: " + getOpportunitiesByIndustry("MANUFACTURING").size() + " opportunities";
        String output4 = "\nMedical: " + getOpportunitiesByIndustry("MEDICAL").size() + " opportunities";
        String output5 = "\nOther: " + getOpportunitiesByIndustry("OTHER").size() + " opportunities";

        return output1 + output2 + output3 + output4 + output5;
    }

    public String findOpportunityByStatusCountByIndustry(String status){
        checkValidStatus(status);
        String output1 = "Produce: " + getOpportunitiesByIndustryAndStatus("PRODUCE", status).size() + " opportunities";
        String output2 = "\ne-Commerce: " + getOpportunitiesByIndustryAndStatus("ECOMMERCE", status).size() + " opportunities";
        String output3 = "\nManufacturing: " + getOpportunitiesByIndustryAndStatus("MANUFACTURING", status).size() + " opportunities";
        String output4 = "\nMedical: " + getOpportunitiesByIndustryAndStatus("MEDICAL", status).size() + " opportunities";
        String output5 = "\nOther: " + getOpportunitiesByIndustryAndStatus("OTHER", status).size() + " opportunities";

        return output1 + output2 + output3 + output4 + output5;
    }

    public String findOpportunityCountByCity() {
        List<String> cities = accountClient.getCities();
        String output = "Holi";
        for (String c : cities){
            output += "\n" + c + ": " + getOpportunitiesByCity(c).size() + " opportunities";
        }

        return output;
    }

    public String findOpportunityByStatusCountByCity(String status) {
        Status status1 = checkValidStatus(status);
        List<String> cities = accountClient.getCities();
        String output = "";
        for (String c : cities){
            output += "\n" + c + ": " + getOpportunitiesByCityAndStatus(c, status1.toString()).size() + " opportunities";
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

    public String findOpportunityByStatusCountByCountry(String status) {
        checkValidStatus(status);
        List<String> countries = accountClient.getCountries();
        String output = "";
        for (String c : countries){
            output += "\n" + c + ": " + getOpportunitiesByCountryAndStatus(c, status).size() + " opportunities";
        }

        return output;
    }

    public BigDecimal getMeanOpportunities(String data) {
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

    public String findOpportunityCountBySalesRep() {
        List<Object[]> result = opportunityRepository.findOpportunityCountBySalesRep();
        return printTwoResults(result);
    }

    public String findOpportunityByStatusCountBySalesRep(String status) {
        Status status1 = checkValidStatus(status);
        List<Object[]> result = opportunityRepository.findOpportunityByStatusCountBySalesRep(status1);
        return printTwoResults(result);
    }


    // AUXILIAR METHODS:

    public String printTwoResults(List<Object[]> result){
        StringBuilder string = new StringBuilder();
        String prefix = "Account id ";
        for (Object[] row : result){
            string.append(prefix + row[0].toString()).append(": ").append((row[1]).toString()).append("\n");
        }
        return string.toString();
    }
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

    public List<OpportunityDTO> returnByAccountID(List<Long> accounts){
        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: accounts){
            List<Opportunity> opportunity = opportunityRepository.findByAccountId(accountId);

            for (Opportunity o:opportunity){
                OpportunityDTO opportunityDTO = new OpportunityDTO(o.getId(), o.getQuantity(),
                        o.getDecisionMakerId(), o.getStatus(), o.getProduct(),
                        o.getRepOpportunityId(),o.getAccountId());
                opportunityDTOList.add(opportunityDTO);
            }
        }
        return opportunityDTOList;
    }

    public List<OpportunityDTO> returnByAccountIDAndStatus(List<Long> accounts, String status){
        //Create a list of OpportunityDTO to store the result
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for(Long accountId: accounts){
            List<Opportunity> opportunities = opportunityRepository.findByAccountId(accountId);

            for (Opportunity o: opportunities){
                if (o.getStatus().toString().equals(status)){
                    OpportunityDTO opportunityDTO = new OpportunityDTO(o.getId(), o.getQuantity(),
                            o.getDecisionMakerId(), o.getStatus(), o.getProduct(),
                            o.getRepOpportunityId(),o.getAccountId());
                    opportunityDTOList.add(opportunityDTO);
                }
            }
        }
        return opportunityDTOList;
    }

    public Status checkValidStatus(String status){
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(status + " is not a valid Status.");
        }
    }

    public void checkValidIndustry(String industry){
        try {
            Industry.valueOf(industry.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(industry + " is not a valid Status.");
        }
    }

}
