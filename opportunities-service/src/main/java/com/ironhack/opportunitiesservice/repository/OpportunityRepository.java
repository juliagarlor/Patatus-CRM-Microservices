package com.ironhack.opportunitiesservice.repository;

import com.ironhack.opportunitiesservice.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
}
