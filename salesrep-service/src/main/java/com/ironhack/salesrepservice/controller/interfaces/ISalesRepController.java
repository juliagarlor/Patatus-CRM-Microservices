package com.ironhack.salesrepservice.controller.interfaces;

import com.ironhack.salesrepservice.controller.dto.SalesRepDTO;
import com.ironhack.salesrepservice.model.SalesRep;

import java.util.List;

public interface ISalesRepController {

    List<SalesRepDTO> findAllSalesRep();


}
