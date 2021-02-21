package com.example.leadservice.controller.impl;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import com.example.leadservice.repository.*;
import com.example.leadservice.services.impl.LeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LeadController {

    @Autowired
    private LeadService leadService;

    @Autowired
    private LeadRepository leadRepository;

    @GetMapping("/leads")
    @ResponseStatus(HttpStatus.OK)
    public List<LeadDTO> findAll() throws ResponseStatusException {

        try {

            return leadService.getAllLeads();

        }catch (Exception exc){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/leads/{lead_id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO findById(@PathVariable Integer lead_id) throws ResponseStatusException{

        try {

            return leadService.getById(lead_id);

        } catch (Exception exc) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/leads/count/{salesRepId}")
    @ResponseStatus(HttpStatus.OK)
    public String findLeadCountBySalesRep() {
        List<Object[]> result = leadRepository.findLeadCountBySalesRep();
        return printTwoResults(result);
    }

    @PostMapping("/lead")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadDTO createLead(@RequestParam String name,@RequestParam String phoneNumber,@RequestParam String email,@RequestParam String companyName, @RequestParam int salesRepId) throws ResponseStatusException {

        try {

            return leadService.createLead(name, phoneNumber, email, companyName, salesRepId);

        } catch (Exception exc) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/lead/{lead_id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO deleteLead(@PathVariable Integer lead_id) throws ResponseStatusException{

        try {

             return leadService.deleteLead(lead_id);

        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // REPORTING:

    public String printTwoResults(List<Object[]> result){
        StringBuilder string = new StringBuilder();
        for (Object[] row : result){
            string.append(row[0].toString()).append(": ").append((row[1]).toString()).append("\n");
        }
        return string.toString();
    }
}
