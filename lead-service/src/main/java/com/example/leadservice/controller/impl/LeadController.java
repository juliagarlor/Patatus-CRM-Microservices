package com.example.leadservice.controller.impl;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import com.example.leadservice.services.impl.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LeadController {

    @Autowired
    private LeadService leadService;

    @GetMapping("/leads")
    @ResponseStatus(HttpStatus.OK)
    public List<Lead> findAll() {

        return leadService.getAllLeads();

    }

    @GetMapping("/leads/{lead_id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO findById(@PathVariable Integer lead_id) {

        return leadService.getById(lead_id);

    }

    @PostMapping("/lead")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadDTO createLead(String name, String phoneNumber, String email, String companyName) {

        return leadService.createLead(name, phoneNumber, email, companyName);

    }

    @DeleteMapping("/lead/{lead_id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO deleteLead(@PathVariable Integer lead_id) throws ResponseStatusException{

        try{

             return leadService.deleteLead(lead_id);

        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
