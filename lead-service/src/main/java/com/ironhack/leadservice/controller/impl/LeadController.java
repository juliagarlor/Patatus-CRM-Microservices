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

    //Get all leads
    @GetMapping("/leads")
    @ResponseStatus(HttpStatus.OK)
    public List<Lead> findAll() {
        return leadService.findAll();
    }
    //Get lead by id
    @GetMapping("/lead/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO findById(@PathVariable Long id) {
        return leadService.findById(id);
    }
    //Get  all the leads of a sales rep
    @GetMapping("/leads/bysalesrep/{salesrepId}")
    @ResponseStatus(HttpStatus.OK)
    public List<LeadDTO> findBySalesrepId(@PathVariable Long salesrepId) {
        return leadService.findBySalesrepId(salesrepId);
    }
    //TODO: cuando le mandas un salesrepId incorrecto, no funciona. eso esta bien, pero no devuelve el error correcto. lo mismo hace falta un circuit breaker
    //Create a lead
    @PostMapping("/lead")
    @ResponseStatus(HttpStatus.CREATED)
    public Lead createLead(@RequestBody @Valid LeadDTO leadDTO) {
        return leadService.createLead(leadDTO);
    }
    //Delete a lead
    @DeleteMapping("/lead/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
    }
    //get a list with a count of leads by sales rep
    @GetMapping("/leads/count/bysalesrep")
    @ResponseStatus(HttpStatus.OK)
    public String findLeadCountBySalesRepId() {
        return leadService.findLeadCountBySalesRepId();
    }
}
