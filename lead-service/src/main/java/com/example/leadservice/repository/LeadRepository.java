package com.example.leadservice.repository;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {


    List<Lead> findByRepLead(Long repLead);
    //A count of Leads by SalesRep
    @Query("SELECT s.name, COUNT(l) FROM Lead l GROUP BY s.id")
    List<Object[]> findLeadCountBySalesRep();

}
