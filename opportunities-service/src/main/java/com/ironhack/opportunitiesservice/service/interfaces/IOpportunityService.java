package com.ironhack.opportunitiesservice.service.interfaces;

import com.ironhack.opportunitiesservice.controller.dto.AccountIdDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityStatusDTO;
import com.ironhack.opportunitiesservice.enums.Industry;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;

import java.util.List;

public interface IOpportunityService {

    //Get method: get all the opportunities
    List<OpportunityDTO> getAllOpportunities();
    //Get method: get an Opportunity by id
    OpportunityDTO getOpportunityById(Long id);
    //Get method: get an opportunity list by sales rep
    List<OpportunityDTO> getOpportunitiesBySalesRep(Long salesRepId);
    //Get method: get an opportunity list by sales rep and status
    List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(Long salesRepId, Status status);
    //Get method:get an opportunity list by country
    List<OpportunityDTO> getOpportunitiesByCountry(String country);
    //Get method:get an opportunity list by country and status
    List<OpportunityDTO> getOpportunitiesByCountryAndStatus(String country, Status status);
    //Get method:get an opportunity list by city
    List<OpportunityDTO> getOpportunitiesByCity(String city);
    //Get method:get an opportunity list by city and status
    List<OpportunityDTO> getOpportunitiesByCityAndStatus(String city, Status status);
    //Get method: get and opportunity list by industry
    List<OpportunityDTO> getOpportunitiesByIndustry(Industry industry);
    //Get method: get and opportunity list by industry and status
    List<OpportunityDTO> getOpportunitiesByIndustryAndStatus(Industry industry, Status status);
    //Post method: create a new Opportunity
    Opportunity createOpportunity(OpportunityDTO opportunityDTO);
    //Patch method: modify the status of the opportunity
    void updateOpportunityStatus(Long id, OpportunityStatusDTO opportunityStatusDTO);
    //Patch method: modify the accountId of the opportunity
    void updateOpportunityAccountId(Long id, AccountIdDTO accountIdDTO);


}
