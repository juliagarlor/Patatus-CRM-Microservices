package com.example.leadservice.services.impl;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import com.example.leadservice.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public List<Lead> getAllLeads(){

        return leadRepository.findAll();

    }

    public LeadDTO getById(Integer id) {

        return new LeadDTO(leadRepository.findById(id).get());

    }

    public LeadDTO createLead(String name, String phoneNumber, String email, String companyName) {

        return new LeadDTO(leadRepository.save(new Lead(name, phoneNumber, email, companyName)));

    }

    public LeadDTO deleteLead(Integer id) throws Exception {

        Optional<Lead> lead = leadRepository.findById(id);

        if (lead.isPresent()) {

            LeadDTO leadDTO = new LeadDTO(lead.get());
            leadRepository.deleteById(id);

            return leadDTO;
        }

        throw new Exception("NOT_FOUND");
    }


}
