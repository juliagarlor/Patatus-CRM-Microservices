package com.ironhack.leadservice.controller.impl;

import com.ironhack.leadservice.controller.interfaces.ILeadController;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.model.Lead;
import com.ironhack.leadservice.service.impl.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LeadController implements ILeadController {

    @Autowired
    private LeadService leadService;


    @GetMapping("/leads")
    @ResponseStatus(HttpStatus.OK)
    public List<Lead> findAll() {
        return leadService.findAll();
    }

    @GetMapping("/lead/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Lead findById(@PathVariable Long id) {
        return leadService.findById(id);
    }

    @GetMapping("/leads/bysalesrep/{salesrepId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Lead> findBySalesrepId(@PathVariable Long salesrepId) {
        return leadService.findBySalesrepId(salesrepId);
    }

    @PostMapping("/lead")
    public Lead createLead(@RequestBody @Valid LeadDTO leadDTO) {
        return leadService.createLead(leadDTO);
    }

    @DeleteMapping("/lead/{id}")
    public void deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
    }

    @GetMapping("/leads/count/bysalesrep")
    @ResponseStatus(HttpStatus.OK)
    public String findLeadCountBySalesRep() {
        return leadService.findLeadCountBySalesRep();
    }
}
