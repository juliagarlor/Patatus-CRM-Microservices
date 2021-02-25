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

    Long getSalesRepId(Long id);

    //Post method: Create a new sales rep
    SalesRep createSalesRep(SalesRepDTO salesRepDTO);

}
