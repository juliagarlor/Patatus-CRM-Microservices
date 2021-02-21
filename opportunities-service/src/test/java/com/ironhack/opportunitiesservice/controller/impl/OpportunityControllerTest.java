package com.ironhack.opportunitiesservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.opportunitiesservice.client.*;
import com.ironhack.opportunitiesservice.enums.*;
import com.ironhack.opportunitiesservice.model.*;
import com.ironhack.opportunitiesservice.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class OpportunityControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

//        Run account service and add some accounts before these tests
        Opportunity opportunity1 = new Opportunity(12, 1L, Status.OPEN, Product.HYBRID,
                1L, 1L);
        Opportunity opportunity2 = new Opportunity(13, 2L, Status.CLOSED_LOST, Product.BOX,
                2L, 1L);
        Opportunity opportunity3 = new Opportunity(14, 1L, Status.OPEN, Product.BOX,
                1L, 2L);

        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
    }

    @Test
    void getMeanOpportunities() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/mean/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1.5"));

        MvcResult result2 = mockMvc.perform(get("/stats/mean/quantity")).andReturn();
        assertTrue(result2.getResponse().getContentAsString().contains("13"));
    }

    @Test
    void getMaxOpportunities() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/max/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("2"));
    }

    @Test
    void getMaxQuantity() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/max/quantity")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("14"));
    }

    @Test
    void getMinOpportunities() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/min/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1"));
    }

    @Test
    void getMinQuantity() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/min/quantity")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12"));
    }

    @Test
    void getMedian() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/median/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1.5"));

        MvcResult result2 = mockMvc.perform(get("/stats/median/quantity")).andReturn();
        assertTrue(result2.getResponse().getContentAsString().contains("13.0"));
    }

    @Test
    void findOpportunityCountBySalesRep() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-salesRep")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1: 2"));
        assertTrue(result.getResponse().getContentAsString().contains("2: 1"));
    }

    @Test
    void findOpportunityByStatusCountBySalesRep() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-salesRep/OPEN")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1: 2"));
    }

    @Test
    void findOpportunityCountByIndustry() {
    }

    @Test
    void findOpportunityByStatusCountByIndustry() {
    }

    @Test
    void findOpportunityCountByCity() {
    }

    @Test
    void findOpportunityByStatusCountByCity() {
    }

    @Test
    void findOpportunityCountByCountry() {
    }

    @Test
    void findOpportunityByStatusCountByCountry() {
    }
}