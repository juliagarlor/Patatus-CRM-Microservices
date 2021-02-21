package com.example.leadservice.services.impl;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import com.example.leadservice.repository.LeadRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public List<LeadDTO> getAllLeads() throws  NotFoundException{

        List<Lead> leads = leadRepository.findAll();

        if(leads.isEmpty()) {

            throw new NotFoundException("NOT_FOUND");

        }

        List<LeadDTO> leadDTOs = new ArrayList<LeadDTO>();

        for(Lead lead : leads){
            leadDTOs.add(new LeadDTO(lead));
        }

        return leadDTOs;

    }

    public LeadDTO getById(Integer id) throws NotFoundException {

        Optional<Lead> lead = leadRepository.findById(id);

        if (lead.isPresent()) {

            return new LeadDTO(lead.get());

        }

        throw new NotFoundException ("NOT_FOUND");

    }

    public LeadDTO createLead(String name, String phoneNumber, String email, String companyName, int salesRepId) throws Exception {

        try {

            return new LeadDTO(leadRepository.save(new Lead(name, phoneNumber, email, companyName, salesRepId)));

        } catch (Exception exc) {

            throw new Exception("AN_ERROR_HAS_OCCURRED");

        }



    }

    public LeadDTO deleteLead(Integer id) throws Exception {

        Optional<Lead> lead = leadRepository.findById(id);

        if (lead.isPresent()) {

            LeadDTO leadDTO = new LeadDTO(lead.get());
            leadRepository.deleteById(id);

            return leadDTO;
        }

        throw new NotFoundException ("NOT_FOUND");
    }


}
