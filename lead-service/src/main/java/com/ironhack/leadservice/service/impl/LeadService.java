package com.ironhack.leadservice.service.impl;

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

    public List<Lead> findAll() {
        List<Lead> leads = leadRepository.findAll();
        return leads;
    }

    public Lead findById(Long id) {
        Optional<Lead> lead = leadRepository.findById(id);
        if (lead.isPresent()) {
            return lead.get();
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
        lead.setName(leadDTO.getName());
        lead.setPhoneNumber(leadDTO.getPhoneNumber());
        lead.setEmail(leadDTO.getEmail());
        lead.setCompanyName(leadDTO.getCompanyName());
        lead.setSalesrepId(leadDTO.getSalesrepId());

        return leadRepository.save(lead);
    }

    public void deleteLead(Long id) {
        Lead lead = findById(id);
        leadRepository.delete(lead);
    }

    public String findLeadCountBySalesRep() {
        return printTwoResults(leadRepository.findLeadCountBySalesrep());
    }


    public String printTwoResults(List<Object[]> result){
        StringBuilder string = new StringBuilder();
        for (Object[] row : result){
            string.append(row[0].toString()).append(": ").append((row[1]).toString()).append("\n");
        }
        return string.toString();
    }
}
