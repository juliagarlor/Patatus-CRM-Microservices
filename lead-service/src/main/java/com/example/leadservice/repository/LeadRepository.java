package com.example.leadservice.repository;

import com.example.leadservice.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByRepLead(Long id);

}
