package com.ironhack.opportunitiesservice.controller.interfaces;

import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.enums.Industry;
import com.ironhack.opportunitiesservice.enums.Status;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IOpportunityController {

    //Get method: get a list with all the opportunities
    List<OpportunityDTO> getOpportunitiesBy(Optional<Integer> salesRepId, Optional<Status> status,
                                            Optional<String> country, Optional<String> city,
                                            Optional<Industry> industry);
    //Get method: get a opportunityDTO by id
    OpportunityDTO getOpportunityDTOById(int id);
}
