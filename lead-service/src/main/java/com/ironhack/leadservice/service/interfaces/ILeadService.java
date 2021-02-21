package com.ironhack.leadservice.service.interfaces;

import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.model.Lead;

import java.util.List;

public interface ILeadService {
    List<Lead> findAll();
    Lead findById(Long id);
    List<Lead> findBySalesrepId(Long salesrepId);
    Lead createLead(LeadDTO leadDTO);
    void deleteLead(Long id);
    String findLeadCountBySalesRep();
}
