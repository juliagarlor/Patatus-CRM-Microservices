package com.ironhack.salesrepservice.service.interfaces;


import com.ironhack.salesrepservice.controller.dto.LeadDTO;
import com.ironhack.salesrepservice.controller.dto.OpportunityDTO;
import com.ironhack.salesrepservice.controller.dto.SalesRepDTO;
import com.ironhack.salesrepservice.enums.Status;
import com.ironhack.salesrepservice.model.SalesRep;

import java.util.List;

public interface ISalesRepService {

    //Get method: get a list with all the sales rep
    List<SalesRepDTO> findAll();

    //Get method: get a list with the leads of a sales rep
    List<LeadDTO> getLeadsBySalesRepId(int id);

    //Get method: get the number of leads by sales rep
    Integer getCountOfLeadsBySalesRepId(int id);

    //Get method: get a list with the opportunities of a sales rep
    List<OpportunityDTO> getOpportunitiesBySalesRepId(int id);

    //Get method: get the number of opportunities by sales rep
    Integer getCountOfOpportunitiesBySalesRepId(int id);

    //Get method: get a list with the opportunities of a sales rep, ordered by status, closed-won or closed-lost
    List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(int id, Status status);

    //Post method: Create a new sales rep
    SalesRep createSalesRep(SalesRepDTO salesRepDTO);

}
