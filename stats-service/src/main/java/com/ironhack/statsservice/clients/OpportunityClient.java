package com.ironhack.statsservice.clients;

import com.ironhack.statsservice.enums.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

@FeignClient("opportunityService-dev")
public interface OpportunityClient {

    @GetMapping("/stats/mean/{data}")
    BigDecimal getMeanOpportunities(@PathVariable String data);

    @GetMapping("/stats/max/opportunities")
    List<Object[]> getMaxOpportunities();

    @GetMapping("/stats/max/quantity")
    int getMaxQuantity();

    @GetMapping("/stats/min/opportunities")
    List<Object[]> getMinOpportunities();

    @GetMapping("/stats/min/quantity")
    int getMinQuantity();

    @GetMapping("/stats/median/{data}")
    double getMedian(@PathVariable String data);

    @GetMapping("/opportunities/count/by-salesRep")
    String findOpportunityCountBySalesRep();

    @GetMapping("/opportunities/count/by-salesRep/{status}")
    String findOpportunityByStatusCountBySalesRep(@PathVariable Status status);

    @GetMapping("/opportunities/count/by-industry")
    String findOpportunityCountByIndustry();

    @GetMapping("/opportunities/count/by-industry/{status}")
    String findOpportunityByStatusCountByIndustry(@PathVariable Status status);

    @GetMapping("/opportunities/count/by-city/{city}")
    String findOpportunityCountByCity();

    @GetMapping("/opportunities/count/by-city/{status}")
    String findOpportunityByStatusCountByCity(@PathVariable Status status);

    @GetMapping("/opportunities/count/by-country")
    String findOpportunityCountByCountry();

    @GetMapping("/opportunities/count/by-country/{status}")
    String findOpportunityByStatusCountByCountry(@PathVariable Status status);
}
