package com.ironhack.contactservice.client;

import com.ironhack.contactservice.controller.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("leadService-dev")
public interface LeadClient {

    @GetMapping("/lead/{id}")
    LeadDTO getLeadDTOById(@PathVariable Long id);

    @DeleteMapping("/lead/{id}")
    void deleteLead(@PathVariable Long id);

}
