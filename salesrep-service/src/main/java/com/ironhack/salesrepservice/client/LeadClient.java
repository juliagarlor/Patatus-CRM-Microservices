package com.ironhack.salesrepservice.client;

import com.ironhack.salesrepservice.controller.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("leadService-dev")
public interface LeadClient {

    //TODO: lo mismo habria que hacer una ruta nueva el lead que envie unicamente las leads con un id de sales rep concreto. ahora mismo lo hare contodo, pero podriamos cambiarlo
    @GetMapping("/leads")
    List<LeadDTO> getAllLeads();

}
