package com.ironhack.opportunitiesservice.repository;

import com.ironhack.opportunitiesservice.enums.Status;
import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    //method to get an opportunity list with an specific sales rep
    List<Opportunity> findByRepOpportunityId(Long id);
    //method to get an opportunity list with an specific sales rep and an specific status
    List<Opportunity> findByRepOpportunityIdAndStatus(Long id, Status status);
    //method to get an opportunity by accountId
    Optional<Opportunity> findByAccountId(Long id);
}
