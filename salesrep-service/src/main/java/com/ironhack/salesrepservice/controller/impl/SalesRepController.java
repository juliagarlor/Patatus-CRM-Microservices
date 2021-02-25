package com.ironhack.salesrepservice.controller.impl;

import com.ironhack.salesrepservice.controller.dto.LeadDTO;
import com.ironhack.salesrepservice.controller.dto.OpportunityDTO;
import com.ironhack.salesrepservice.controller.dto.SalesRepDTO;
import com.ironhack.salesrepservice.controller.interfaces.ISalesRepController;
import com.ironhack.salesrepservice.enums.Status;
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
    //get a list with all the salesreps
    @GetMapping("/salesreps")
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRepDTO> findAllSalesRep() {
        return salesRepService.findAll();
    }
    //return the id of a sales rep. is used to check if a sales rep exist at the moment of creating an account
    @GetMapping("/salesrep/{id}/id")
    public Long getSalesRepId(@PathVariable Long id) {
        return salesRepService.getSalesRepId(id);
    }

    //get the leads of a sales rep
    @GetMapping("/salesreps/{sales_id}/leads")
    @ResponseStatus(HttpStatus.OK)
    public List<LeadDTO> getLeadsBySalesRepId(@PathVariable("sales_id") Long id) {
        return salesRepService.getLeadsBySalesRepId(id);
    }

    @GetMapping("/salesreps/{sales_id}/leads/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer getCountOfLeadsBySalesRepId(@PathVariable("sales_id") Long id) {
        return salesRepService.getCountOfLeadsBySalesRepId(id);
    }

    @GetMapping("/salesreps/{sales_id}/opportunities")
    @ResponseStatus(HttpStatus.OK)
    public List<OpportunityDTO> getOpportunitiesBySalesRepId(@PathVariable("sales_id") Long id) {
        return salesRepService.getOpportunitiesBySalesRepId(id);
    }

    @GetMapping("/salesreps/{sales_id}/opportunities/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer getCountOfOpportunitiesBySalesRepId(@PathVariable("sales_id") Long id) {
        return salesRepService.getCountOfOpportunitiesBySalesRepId(id);
    }

    //TODO: o dejarlo asi, o meter en la ruta de pillar las oportunidades por sales rep y hacer unos cuantos if
    @GetMapping("/salesreps/{sales_id}/opportunities/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(@PathVariable("sales_id") Long id, @PathVariable String status) {
        return salesRepService.getOpportunitiesBySalesRepAndStatus(id, status);
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
