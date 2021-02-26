package com.ironhack.leadservice.service.impl;

import com.ironhack.leadservice.client.SalesrepClient;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.model.Lead;
import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.service.interfaces.ILeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService implements ILeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private SalesrepClient salesrepClient;


    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<LeadDTO> findAll() {
        List<Lead> leads = leadRepository.findAll();
        List<LeadDTO> output = new ArrayList<>();
        for(Lead l:leads){
            output.add(new LeadDTO(l));
        }
        return output;
    }

    public LeadDTO findById(Long id) {
        Optional<Lead> lead = leadRepository.findById(id);
        if (lead.isPresent()) {

            return new LeadDTO(lead.get().getId(), lead.get().getName(), lead.get().getPhoneNumber(), lead.get().getEmail(), lead.get().getCompanyName(), lead.get().getSalesrepId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No lead with id " + id + " present.");
        }
    }

    public List<LeadDTO> findBySalesrepId(Long salesrepId) {
        List<Lead> leads = leadRepository.findBySalesrepId(salesrepId);
        List<LeadDTO> leadDTOS = new ArrayList<>();
        for (Lead lead: leads) {
            LeadDTO leadDTO = new LeadDTO();
            leadDTO.setId(lead.getId());
            leadDTO.setName(lead.getName());
            leadDTO.setPhoneNumber(lead.getPhoneNumber());
            leadDTO.setEmail(lead.getEmail());
            leadDTO.setCompanyName(lead.getCompanyName());
            leadDTO.setSalesrepId(lead.getSalesrepId());
            leadDTOS.add(leadDTO);
        }

        return leadDTOS;
    }

    public LeadDTO createLead(LeadDTO leadDTO) {
        Lead lead = new Lead();
        lead.setSalesrepId(salesrepClient.getSalesRepId(leadDTO.getSalesrepId()));
        lead.setName(leadDTO.getName());
        lead.setPhoneNumber(leadDTO.getPhoneNumber());
        lead.setEmail(leadDTO.getEmail());
        lead.setCompanyName(leadDTO.getCompanyName());
        leadRepository.save(lead);
        return leadDTO;
    }

    private Long salesRepFallback() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "salesRepService-dev down");
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
            string.append("Salesrep ID " + row[0].toString()).append(": ").append((row[1]).toString()).append("\n");
        }
        return string.toString();
    }
}
