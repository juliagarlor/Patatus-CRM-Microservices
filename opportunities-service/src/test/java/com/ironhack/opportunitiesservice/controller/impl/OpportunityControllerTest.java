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
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void getOpportunitiesBy_validCountryAndIdAndStatus_OpportunityDTOList() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities?country=DisneyLand")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("14"));
        assertFalse(result.getResponse().getContentAsString().contains("13"));
        assertFalse(result.getResponse().getContentAsString().contains("12"));

        MvcResult result2 = mockMvc.perform(get("/opportunities?status=CLOSED_LOST&salesrep-id=2"
        )).andReturn();
        assertTrue(result2.getResponse().getContentAsString().contains("13"));
        assertFalse(result2.getResponse().getContentAsString().contains("14"));
        assertFalse(result2.getResponse().getContentAsString().contains("12"));
    }

    @Test
    void getOpportunityDTOById_validId_OpportunityDTO() throws Exception {
        List<Opportunity> opportunities = opportunityRepository.findAll();
        MvcResult result = mockMvc.perform(get("/opportunity/" + opportunities.get(0).getId())).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12"));
        assertFalse(result.getResponse().getContentAsString().contains("13"));
        assertFalse(result.getResponse().getContentAsString().contains("14"));
    }

    @Test
    void createOpportunity_validDTO_OpportunityDTO() throws Exception {
        OpportunityDTO opportunityDTO = new OpportunityDTO(15, 2L, Status.OPEN,
                Product.BOX, 2L);
        String body = objectMapper.writeValueAsString(opportunityDTO);
        MvcResult result = mockMvc.perform(
                post("/opportunity")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("15"));
        assertEquals(4, opportunityRepository.findAll().size());
    }

    @Test
    void updateOpportunityStatus_validStatus_OpportunityDTO() throws Exception {
        List<Opportunity> opportunities = opportunityRepository.findAll();
        OpportunityStatusDTO opportunityStatusDTO = new OpportunityStatusDTO(Status.CLOSED_WON);
        String body = objectMapper.writeValueAsString(opportunityStatusDTO);
        MvcResult result = mockMvc.perform(
                patch("/opportunity/"+ opportunities.get(0).getId() + "/status")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isAccepted()).andReturn();

        assertEquals(Status.CLOSED_WON, opportunityRepository.findAll().get(0).getStatus());
    }

    @Test
    void updateOpportunityAccountId_validId_OpportunityDTO() throws Exception {
        List<Opportunity> opportunities = opportunityRepository.findAll();
        AccountIdDTO accountIdDTO = new AccountIdDTO(opportunities.get(2).getAccountId());
        String body = objectMapper.writeValueAsString(accountIdDTO);
        MvcResult result = mockMvc.perform(
                patch("/opportunity/"+ opportunities.get(0).getId() + "/account-id")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isAccepted()).andReturn();

        assertEquals(opportunities.get(2).getAccountId(), opportunityRepository.findAll().get(0).getAccountId());
    }

    @Test
    void getMeanOpportunities_nothing_BigDecimal() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/mean/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1.5"));

        MvcResult result2 = mockMvc.perform(get("/stats/mean/quantity")).andReturn();
        assertTrue(result2.getResponse().getContentAsString().contains("13"));
    }

    @Test
    void getMaxOpportunities_nothing() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/max/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("2"));
    }

    @Test
    void getMaxQuantity_nothing_maxQuantity() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/max/quantity")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("14"));
    }

    @Test
    void getMinOpportunities_nothing_salesRepIdAndAmount() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/min/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1"));
    }

    @Test
    void getMinQuantity_nothing_minQuantity() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/min/quantity")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12"));
    }

    @Test
    void getMedian_nothing_BigDecimal() throws Exception {
        MvcResult result = mockMvc.perform(get("/stats/median/opportunities")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1.5"));

        MvcResult result2 = mockMvc.perform(get("/stats/median/quantity")).andReturn();
        assertTrue(result2.getResponse().getContentAsString().contains("13.0"));
    }

    @Test
    void findOpportunityCountBySalesRep_nothing_salesRepIdAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-salesRep")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1: 2"));
        assertTrue(result.getResponse().getContentAsString().contains("2: 1"));
    }

    @Test
    void findOpportunityByStatusCountBySalesRep_validStatus_salesRepIdAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-salesRep/OPEN")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1: 2"));
    }

    @Test
    void findOpportunityCountByIndustry_nothing_industryAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-industry")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Produce: 0 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("e-Commerce: 3 opportunities"));
    }

    @Test
    void findOpportunityByStatusCountByIndustry_validStatus_industryAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-industry/CLOSED_LOST")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Produce: 0 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("e-Commerce: 1 opportunities"));
    }

    @Test
    void findOpportunityCountByCity_nothing_cityAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-city")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Madrid: 2 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("Far West: 1 opportunities"));
    }

    @Test
    void findOpportunityByStatusCountByCity_validStatus_cityAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-city/CLOSED_LOST")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Madrid: 1 opportunities"));
    }

    @Test
    void findOpportunityCountByCountry_nothing_countryAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-country")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Essspaña: 2 opportunities"));
        assertTrue(result.getResponse().getContentAsString().contains("DisneyLand: 1 opportunities"));
    }

    @Test
    void findOpportunityByStatusCountByCountry_validStatus_countryAndCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/opportunities/count/by-country/CLOSED_LOST")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Essspaña: 1 opportunities"));
    }
}