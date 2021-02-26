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

    //Post method: create a new sales rep
    SalesRep createSalesRep(SalesRepDTO salesRepDTO);


}

