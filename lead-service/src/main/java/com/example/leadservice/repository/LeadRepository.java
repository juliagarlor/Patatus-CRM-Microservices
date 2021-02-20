package com.example.leadservice.repository;

import com.example.leadservice.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Integer> {



}
