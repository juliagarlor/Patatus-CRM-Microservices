package com.ironhack.salesrepservice.service.impl;

import com.ironhack.salesrepservice.Repository.SalesRepRepository;
import com.ironhack.salesrepservice.client.OpportunityClient;
import com.ironhack.salesrepservice.controller.dto.LeadDTO;
import com.ironhack.salesrepservice.controller.dto.OpportunityDTO;
import com.ironhack.salesrepservice.controller.dto.SalesRepDTO;
import com.ironhack.salesrepservice.enums.Status;
import com.ironhack.salesrepservice.model.SalesRep;
import com.ironhack.salesrepservice.service.interfaces.ISalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesRepService implements ISalesRepService {

    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private com.ironhack.salesrepservice.client.LeadClient leadClient;
    @Autowired
    private OpportunityClient opportunityClient;

    //===========================================
    //Get methods
    //===========================================

    public List<SalesRepDTO> findAll() {

        List<SalesRepDTO> salesRepDTOS = new ArrayList<SalesRepDTO>();

        for(SalesRep salesRep: salesRepRepository.findAll()){
            SalesRepDTO salesRepDTO = new SalesRepDTO(salesRep.getId(), salesRep.getName());
            salesRepDTOS.add(salesRepDTO);
        }

        return salesRepDTOS;
    }

    public List<LeadDTO> getLeadsBySalesRepId(Long id) {

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        List<LeadDTO> leadDTOList = leadClient.getAllLeads();
        SalesRep salesRep = salesRepRepository.findById(id).get();

        List<LeadDTO> newLeadDTOList = new ArrayList<>();

        for(LeadDTO leadDTO: leadDTOList){
            if (salesRep.getId().equals(leadDTO.getSalesRepId())){
                newLeadDTOList.add(leadDTO);
            }
        }
        return newLeadDTOList;
    }

    public Integer getCountOfLeadsBySalesRepId(Long id){

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        List<LeadDTO> leadDTOList = leadClient.getAllLeads();
        SalesRep salesRep = salesRepRepository.findById(id).get();

        Integer count = 0;

        for(LeadDTO leadDTO: leadDTOList){
            if (salesRep.getId().equals(leadDTO.getSalesRepId())){
                count += 1;
            }
        }
        return count;
    }

    public List<OpportunityDTO> getOpportunitiesBySalesRepId(Long id) {

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        return opportunityClient.getOpportunitiesBySalesRep(id);
    }

    public Integer getCountOfOpportunitiesBySalesRepId(Long id){

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        Integer count = 0;

        for (OpportunityDTO opportunityDTO: opportunityClient.getOpportunitiesBySalesRep(id)){
            count+= 1;
        }

        return count;
    }

    public List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(Long id, Status status) {

        if(salesRepRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sales rep with id " + id + " not found");
        }

        return opportunityClient.getOpportunitiesBySalesRepAndStatus(id, status);
    }

    //=========================================================
    //Post Methods
    //=========================================================

    public SalesRep createSalesRep(SalesRepDTO salesRepDTO) {

        SalesRep salesRep = new SalesRep(salesRepDTO.getName());
        salesRepRepository.save(salesRep);

        return salesRep;
    }

}
