package com.ironhack.leadservice.service.impl;

import com.ironhack.leadservice.client.SalesrepClient;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.model.Lead;
import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.service.interfaces.ILeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService implements ILeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private SalesrepClient salesrepClient;

    public List<Lead> findAll() {
        List<Lead> leads = leadRepository.findAll();
        return leads;
    }

    public LeadDTO findById(Long id) {
        Optional<Lead> lead = leadRepository.findById(id);
        if (lead.isPresent()) {

            return new LeadDTO(lead.get().getId(), lead.get().getName(), lead.get().getPhoneNumber(), lead.get().getEmail(), lead.get().getCompanyName(), lead.get().getSalesrepId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No lead with id " + id + " present.");
        }
    }

    public List<Lead> findBySalesrepId(Long salesrepId) {
        List<Lead> leads = leadRepository.findBySalesrepId(salesrepId);
        return leads;
    }

    public Lead createLead(LeadDTO leadDTO) {
        Lead lead = new Lead();
        try {
            lead.setSalesrepId(salesrepClient.getSalesRepId(leadDTO.getSalesrepId()));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no salesrip with ID: " + leadDTO.getSalesrepId());
        }
        lead.setName(leadDTO.getName());
        lead.setPhoneNumber(leadDTO.getPhoneNumber());
        lead.setEmail(leadDTO.getEmail());
        lead.setCompanyName(leadDTO.getCompanyName());

        return leadRepository.save(lead);
    }

    public void deleteLead(Long id) {

        Optional<Lead> lead = leadRepository.findById(id);
        if (lead.isPresent()) {
            leadRepository.delete(lead.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No lead with id " + id + " present.");
        }
    }

    public String findLeadCountBySalesRepId() {
        return printTwoResults(leadRepository.findLeadCountBySalesrepId());
    }


    public String printTwoResults(List<Object[]> result){
        StringBuilder string = new StringBuilder();
        for (Object[] row : result){
            string.append(row[0].toString()).append(": ").append((row[1]).toString()).append("\n");
        }
        return string.toString();
    }
}
