package com.ironhack.leadservice.service.interfaces;

import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.model.Lead;

import java.util.List;

public interface ILeadService {
    List<LeadDTO> findAll();
    LeadDTO findById(Long id);
    List<LeadDTO> findBySalesrepId(Long salesrepId);
    LeadDTO createLead(LeadDTO leadDTO);
    void deleteLead(Long id);
    String findLeadCountBySalesRepId();
}
