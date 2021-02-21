package com.ironhack.leadservice.repository;


import com.ironhack.leadservice.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByRepLead(long id);

}
