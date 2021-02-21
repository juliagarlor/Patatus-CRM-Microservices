package com.ironhack.contactservice.client;

import com.ironhack.contactservice.controller.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("leadService-dev")
public interface LeadClient {

    @GetMapping("/lead/{leadId}")
    LeadDTO getLeadDTOById(@PathVariable Long leadId);

}
