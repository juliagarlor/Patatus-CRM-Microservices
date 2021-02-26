package com.ironhack.salesrepservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.salesrepservice.Repository.SalesRepRepository;
import com.ironhack.salesrepservice.client.*;
import com.ironhack.salesrepservice.controller.dto.*;
import com.ironhack.salesrepservice.model.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SalesRepControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private LeadClient leadClient;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        SalesRep salesRep1 = new SalesRep("Pepe");
        SalesRep salesRep2 = new SalesRep("Mari Carmen");
        salesRepRepository.saveAll(List.of(salesRep1, salesRep2));
    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();
    }

    @Test
    void findAllSalesRep() throws Exception {
        MvcResult result = mockMvc.perform(get("/salesreps")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pepe"));
        assertTrue(result.getResponse().getContentAsString().contains("Mari Carmen"));
    }

    @Test
    void getSalesRepId() throws Exception {
        SalesRep salesRep = salesRepRepository.findAll().get(0); // To avoid auto increment problems.
        MvcResult result = mockMvc.perform(get("/salesrep/" + salesRep.getId()  + "/id")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(salesRep.getId().toString()));
    }

    @Test
    void createSalesRep() throws Exception {
        SalesRepDTO salesRepDTO = new SalesRepDTO("Juan");
        String body = objectMapper.writeValueAsString(salesRepDTO);
        MvcResult result = mockMvc.perform(
                post("/salesrep")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Juan"));

        List<SalesRep> salesReps = salesRepRepository.findAll();
        assertEquals(3, salesReps.size());
    }
}