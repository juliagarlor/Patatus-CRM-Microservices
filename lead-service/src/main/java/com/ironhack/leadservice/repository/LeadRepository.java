package com.ironhack.leadservice.repository;


import com.ironhack.leadservice.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findBySalesrepId(Long id);

    @Query("SELECT salesrepId, COUNT(id) FROM Lead GROUP BY salesrepId")
    List<Object[]> findLeadCountBySalesrep();
}
