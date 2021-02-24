package com.ironhack.edgeservice.client;


import com.ironhack.edgeservice.controller.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("opportunityService-dev")
public interface OpportunityClient {

    //This method return a list with the opportunities of a specific sales rep
    @GetMapping("/opportunities")
    List<OpportunityDTO> getOpportunitiesBySalesRep(@RequestParam(name="salesrep-id") Long salesrepId);

    //This method return a list with the opportunities of an specific sales rep and status
    @GetMapping("/opportunities")
    List<OpportunityDTO> getOpportunitiesBySalesRepAndStatus(@RequestParam(name="salesrep-id") Long salesrepId, @RequestParam(name="status") String status);


}
