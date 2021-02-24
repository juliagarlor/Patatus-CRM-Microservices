package com.ironhack.edgeservice.client;


import com.ironhack.edgeservice.controller.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("leadService-dev")
public interface LeadClient {


}
