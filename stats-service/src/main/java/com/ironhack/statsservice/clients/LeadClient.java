package com.ironhack.statsservice.clients;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient("lead-service")
public interface LeadClient {

    @GetMapping("/leads/count/bySalesRep")
    public String findLeadCountBySalesRep();
}
