package com.ironhack.leadservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.model.*;
import com.ironhack.leadservice.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LeadControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private LeadRepository leadRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Lead lead1 = new Lead("Cayetano", "925959295", "cayetano@gmail.com",
                "cayetano SL", 1L);
        Lead lead2 = new Lead("Cayetana", "925959299", "cayetana@gmail.com",
                "chupiguay INC", 1L);
        Lead lead3 = new Lead("Maria Del Carmen", "925959296", "mdelcarmen@gmail.com",
                "cayetano SL", 2L);

        leadRepository.saveAll(List.of(lead1, lead2, lead3));
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult result = mockMvc.perform(get("/leads")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("cayetano@gmail.com"));
        assertTrue(result.getResponse().getContentAsString().contains("cayetana@gmail.com"));
        assertTrue(result.getResponse().getContentAsString().contains("mdelcarmen@gmail.com"));
    }

    @Test
    void findById() throws Exception {
        List<Lead> leads = leadRepository.findAll();    // To avoid auto generate id problems.
        MvcResult result = mockMvc.perform(get("/lead/"+leads.get(0).getId())).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("cayetano@gmail.com"));
        assertFalse(result.getResponse().getContentAsString().contains("cayetana@gmail.com"));
        assertFalse(result.getResponse().getContentAsString().contains("mdelcarmen@gmail.com"));
    }

    @Test
    void findBySalesrepId() throws Exception {
        MvcResult result = mockMvc.perform(get("/leads/bysalesrep/2")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("mdelcarmen@gmail.com"));
        assertFalse(result.getResponse().getContentAsString().contains("cayetano@gmail.com"));
        assertFalse(result.getResponse().getContentAsString().contains("cayetana@gmail.com"));
    }

    @Test
    void createLead() throws Exception {
        LeadDTO leadDTO = new LeadDTO("Cayetanito", "123456789", "as@gmail.com",
                "Cayetano SL", 1L);
        String body = objectMapper.writeValueAsString(leadDTO);
        MvcResult result = mockMvc.perform(
                post("/lead")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Cayetanito"));

        List<Lead> leads = leadRepository.findAll();
        assertEquals(4, leads.size());
    }

    @Test
    void deleteLead() throws Exception {
        List<Lead> leads = leadRepository.findAll();    // To avoid auto generate id problems.
        mockMvc.perform(delete("/lead/"+leads.get(0).getId())).andExpect(status().isNoContent()).andReturn();

        leads = leadRepository.findAll();
        assertEquals(2, leads.size());
    }

    @Test
    void findLeadCountBySalesRep() throws Exception {
        MvcResult result = mockMvc.perform(get("/leads/count/bysalesrep")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("1: 2"));
        assertTrue(result.getResponse().getContentAsString().contains("2: 1"));
    }
}