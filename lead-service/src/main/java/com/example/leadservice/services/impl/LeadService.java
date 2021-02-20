package com.example.leadservice.services.impl;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import com.example.leadservice.repository.LeadRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LeadService {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public LeadDTO getById(Long id) throws NotFoundException {

        Optional<Lead> lead = leadRepository.findById(id);

        if (lead.isPresent()) {

            return new LeadDTO(lead.get());

        }

        throw new NotFoundException ("NOT_FOUND");

    }

    public List<LeadDTO> findByRepLead(Long repleadId) throws NotFoundException{
        List<Lead> leads = leadRepository.findByRepLead(repleadId);

        if (leads.isEmpty()){
            throw new NotFoundException("NOT_FOUND");
        }

        List<LeadDTO> leadDTOs = new ArrayList<LeadDTO>();

        for(Lead lead : leads){
            leadDTOs.add(new LeadDTO(lead));
        }

        return leadDTOs;

    }

    public LeadDTO createLead(String name, String phoneNumber, String email, String companyName, long repLead) throws Exception {

        logger.info(Long.toString(repLead));

        try {

            return new LeadDTO(leadRepository.save(new Lead(name, phoneNumber, email, companyName, repLead)));

        } catch (Exception exc) {

            throw new Exception("AN_ERROR_HAS_OCCURRED");

        }



    }

    public LeadDTO deleteLead(Long id) throws Exception {

        Optional<Lead> lead = leadRepository.findById(id);

        if (lead.isPresent()) {

            LeadDTO leadDTO = new LeadDTO(lead.get());
            leadRepository.deleteById(id);

            return leadDTO;
        }

        throw new NotFoundException ("NOT_FOUND");
    }


}
