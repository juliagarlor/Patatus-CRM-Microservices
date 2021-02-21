package com.example.leadservice.repository;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    //A count of Leads by SalesRep
    @Query("SELECT s.name,COUNT(l) FROM Lead l INNER JOIN l.repLead s GROUP BY s")
    List<Object[]> findLeadCountBySalesRep();

}
