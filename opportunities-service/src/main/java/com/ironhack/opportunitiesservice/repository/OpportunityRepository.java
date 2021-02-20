package com.ironhack.opportunitiesservice.repository;

import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    //method to get an opportunity list with an specific sales rep
    List<Opportunity> findByRepOpportunityId(int id);

}
