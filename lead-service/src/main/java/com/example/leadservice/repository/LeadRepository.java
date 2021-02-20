package com.example.leadservice.repository;

import com.example.leadservice.controller.dto.LeadDTO;
import com.example.leadservice.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Integer> {

    //method that return a list of Leads by sales rep
    List<LeadDTO> findBy

}
