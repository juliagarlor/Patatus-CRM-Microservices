package com.ironhack.opportunitiesservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.opportunitiesservice.client.*;
import com.ironhack.opportunitiesservice.controller.dto.*;
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

//        Creating some accounts
        AccountDTO account1 = new AccountDTO("ECOMMERCE", 10, "Madrid", "Essspaña");
        accountClient.postAccount(account1);
        AccountDTO account2 = new AccountDTO("ECOMMERCE", 20, "Far West", "DisneyLand");
        accountClient.postAccount(account2);

//        Run account service and add some accounts before these tests
        Opportunity opportunity1 = new Opportunity(12, 1L, Status.OPEN, Product.HYBRID,
                1L, accountClient.getAllAccounts().get(0).getId());
        Opportunity opportunity2 = new Opportunity(13, 2L, Status.CLOSED_LOST, Product.BOX,
                2L, accountClient.getAllAccounts().get(0).getId());
        Opportunity opportunity3 = new Opportunity(14, 1L, Status.OPEN, Product.BOX,
                1L, accountClient.getAllAccounts().get(1).getId());

        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        accountClient.deleteAccounts();
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
    void findOpportunityCountByIndustry() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-industry")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Produce: 0 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("e-Commerce: 3 opportunities"));
    }

    @Test
    void findOpportunityByStatusCountByIndustry() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-industry/CLOSED_LOST")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Produce: 0 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("e-Commerce: 1 opportunities"));
    }

    @Test
    void findOpportunityCountByCity() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-city")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Madrid: 2 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("Far West: 1 opportunities"));
    }

    @Test
    void findOpportunityByStatusCountByCity() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-city/CLOSED_LOST")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Madrid: 1 opportunities"));
    }

    @Test
    void findOpportunityCountByCountry() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-country")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Essspaña: 2 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("DisneyLand: 1 opportunities"));
    }

    @Test
    void findOpportunityByStatusCountByCountry() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-country/CLOSED_LOST")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Essspaña: 1 opportunities"));
    }
}