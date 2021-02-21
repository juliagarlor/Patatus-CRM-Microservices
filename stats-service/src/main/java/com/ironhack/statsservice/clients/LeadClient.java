package com.ironhack.statsservice.clients;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient("leadService-dev")
public interface LeadClient {

    @GetMapping("/leads/count/bysalesrep")
    String findLeadCountBySalesRep();
}
