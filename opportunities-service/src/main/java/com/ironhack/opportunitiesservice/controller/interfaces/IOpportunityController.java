package com.ironhack.opportunitiesservice.controller.interfaces;

import com.ironhack.opportunitiesservice.controller.dto.AccountIdDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityDTO;
import com.ironhack.opportunitiesservice.controller.dto.OpportunityStatusDTO;
import com.ironhack.opportunitiesservice.enums.Industry;
import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IOpportunityController {

    //Get method: get a list with all the opportunities
    List<OpportunityDTO> getOpportunitiesBy(Optional<Long> salesRepId, Optional<Status> status,
                                            Optional<String> country, Optional<String> city,
                                            Optional<Industry> industry);
    //Get method: get a opportunityDTO by id
    OpportunityDTO getOpportunityDTOById(Long id);
    //Post method: create a new opportunity
    Opportunity createOpportunity(OpportunityDTO opportunityDTO);
    //Patch method: update the status of a opportunity
    void updateOpportunityStatus(Long id, OpportunityStatusDTO opportunityStatusDTO);
    //Patch method: update the accountId of a opportunity
    void updateOpportunityAccountId(Long id, AccountIdDTO accountIdDTO);
}
