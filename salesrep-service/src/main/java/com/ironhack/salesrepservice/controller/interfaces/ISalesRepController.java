package com.ironhack.salesrepservice.controller.interfaces;

import com.ironhack.salesrepservice.controller.dto.LeadDTO;
import com.ironhack.salesrepservice.controller.dto.OpportunityDTO;
import com.ironhack.salesrepservice.controller.dto.SalesRepDTO;
import com.ironhack.salesrepservice.enums.Status;
import com.ironhack.salesrepservice.model.SalesRep;

import java.util.List;

public interface ISalesRepController {

    List<SalesRepDTO> findAllSalesRep();

    Long getSalesRepId(Long id);

    List<LeadDTO> getLeadsBySalesRepId(Long id);

    //Get method: get the number of leads by sales rep
    Integer getCountOfLeadsBySalesRepId(Long id);

    //Get method: get a list with the opportunities of a sales rep
    List<OpportunityDTO> getOpportunitiesBySalesRepId(Long id);

    //Get method: get the number of opportunities by sales rep
    Integer getCountOfOpportunitiesBySalesRepId(Long id);

    //Get method: get a list with the opportunities of a sales rep, ordered by status, closed-won or closed-lost
    List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(Long id, String status);


    //Post method: create a new sales rep
    SalesRep createSalesRep(SalesRepDTO salesRepDTO);


}

