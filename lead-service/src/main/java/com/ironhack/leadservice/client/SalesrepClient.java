package com.ironhack.leadservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("salesRepService-dev")
public interface SalesrepClient {

    // We use this method to check if salesrep exists when creating a new lead.
    @GetMapping("/salesrep/{id}/id")
    Long getSalesRepId(@PathVariable Long id);
}
