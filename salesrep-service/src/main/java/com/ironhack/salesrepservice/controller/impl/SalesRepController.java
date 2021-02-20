package com.ironhack.salesrepservice.controller.impl;

import com.ironhack.salesrepservice.controller.dto.SalesRepDTO;
import com.ironhack.salesrepservice.controller.interfaces.ISalesRepController;
import com.ironhack.salesrepservice.model.SalesRep;
import com.ironhack.salesrepservice.service.interfaces.ISalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalesRepController implements ISalesRepController {

    @Autowired
    private ISalesRepService salesRepService;

    //===========================================
    //Get methods
    //===========================================

    @GetMapping("/salesreps")
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRepDTO> findAllSalesRep() {
        return salesRepService.findAll();
    }



    //===========================================
    //Post methods
    //===========================================
    @PostMapping("/salesrep")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRep createSalesRep(@RequestBody SalesRepDTO salesRepDTO){
        return salesRepService.createSalesRep(salesRepDTO);
    }



}
