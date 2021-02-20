package com.ironhack.statsservice.clients;

import org.springframework.cloud.openfeign.*;

@FeignClient("opportunityService-dev")
public interface OpportunityClient {


}
