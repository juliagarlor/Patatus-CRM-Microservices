package com.ironhack.salesrepservice.client;

import com.ironhack.salesrepservice.controller.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;

@FeignClient("leadService-dev")
public interface LeadClient {

    //TODO: lo mismo habria que hacer una ruta nueva el lead que envie unicamente las leads con un id de sales rep concreto. ahora mismo lo hare contodo, pero podriamos cambiarlo
    @GetMapping("/leads")
    List<LeadDTO> getAllLeads();

    @GetMapping("/leads/bysalesrep/{id}")
    List<LeadDTO> findBySalesrepId(@PathVariable Long id);

    @PostMapping("/lead")
    LeadDTO createLead(@RequestBody LeadDTO leadDTO);

    @DeleteMapping("/lead/{id}")
    void deleteLead(@PathVariable Long id);

}
