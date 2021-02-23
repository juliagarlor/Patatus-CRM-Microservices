package com.ironhack.leadservice.controller.interfaces;

import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.model.Lead;

import java.util.List;

public interface ILeadController {
    List<Lead> findAll();
    Lead findById(Long id);
    List<Lead> findBySalesrepId(Long salesrepId);
    Lead createLead(LeadDTO leadDTO);
    void deleteLead(Long leadId);
    String findLeadCountBySalesRepId();
}
