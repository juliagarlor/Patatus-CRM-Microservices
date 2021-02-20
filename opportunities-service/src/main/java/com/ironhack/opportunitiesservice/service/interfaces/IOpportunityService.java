package com.ironhack.opportunitiesservice.service.interfaces;

import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.List;

public interface IOpportunityService {

    //Get method: get all the opportunities
    List<OpportunityDTO> getAllOpportunities();
    //Get method: get an Opportunity by id
    OpportunityDTO getOpportunityById(int id);
    //Get method: get an opportunity list by sales rep, status or both
    List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(int salesRepId, Status status);
    //Get method:get an opportunity list by country and status by country
    List<OpportunityDTO> getOpportunitiesByCountryAndStatus(String country, Status status);
    //Get method: get and opportunity list by industry and status or industry
    List<OpportunityDTO> getOpportunitiesByIndustryAndStatus(String industry, Status status);
    //Post method: create a new Opportunity
    Opportunity createOpportunity(OpportunityDTO opportunityDTO);

    BigDecimal getMeanOpportunities(@PathVariable String data);
    List<Object[]> getMaxOpportunities(String data);
    int getMaxQuantity(String data);
    List<Object[]> getMinOpportunities(String data);
    int getMinQuantity(String data);
    double getMedian(@PathVariable String data);
}
