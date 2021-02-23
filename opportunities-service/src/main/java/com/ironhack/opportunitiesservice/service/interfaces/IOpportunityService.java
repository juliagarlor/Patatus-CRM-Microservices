package com.ironhack.opportunitiesservice.service.interfaces;

import com.ironhack.opportunitiesservice.controller.dto.AccountIdDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityStatusDTO;
import com.ironhack.opportunitiesservice.enums.Industry;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.*;
import java.util.List;

public interface IOpportunityService {

    //Get method: get all the opportunities
    List<OpportunityDTO> getAllOpportunities();
    //Get method: get an Opportunity by id
    OpportunityDTO getOpportunityById(Long id);
    //Get method: get an opportunity list by sales rep
    List<OpportunityDTO> getOpportunitiesBySalesRep(Long salesRepId);
    //Get method: get an opportunity list by sales rep and status
    List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(Long salesRepId, String status);
    //Get method:get an opportunity list by country
    List<OpportunityDTO> getOpportunitiesByCountry(String country);
    //Get method:get an opportunity list by country and status
    List<OpportunityDTO> getOpportunitiesByCountryAndStatus(String country, String status);
    //Get method:get an opportunity list by city
    List<OpportunityDTO> getOpportunitiesByCity(String city);
    //Get method:get an opportunity list by city and status
    List<OpportunityDTO> getOpportunitiesByCityAndStatus(String city, String status);
    //Get method: get and opportunity list by industry
    List<OpportunityDTO> getOpportunitiesByIndustry(String industry);
    //Get method: get and opportunity list by industry and status
    List<OpportunityDTO> getOpportunitiesByIndustryAndStatus(String industry, String status);
    //Post method: create a new Opportunity
    Opportunity createOpportunity(OpportunityDTO opportunityDTO);
    //Patch method: modify the status of the opportunity
    void updateOpportunityStatus(Long id, OpportunityStatusDTO opportunityStatusDTO);
    //Patch method: modify the accountId of the opportunity
    void updateOpportunityAccountId(Long id, AccountIdDTO accountIdDTO);

    String findOpportunityCountByIndustry();
    String findOpportunityByStatusCountByIndustry(String status);
    String findOpportunityCountByCity();
    String findOpportunityByStatusCountByCity(String status);
    String findOpportunityCountByCountry();
    String findOpportunityByStatusCountByCountry(String status);
    BigDecimal getMeanOpportunities(String data);
    List<Object[]> getMaxOpportunities();
    int getMaxQuantity();
    List<Object[]> getMinOpportunities();
    int getMinQuantity();
    double getMedian(String data);

    String findOpportunityCountBySalesRep();
    String findOpportunityByStatusCountBySalesRep(String status);
}
