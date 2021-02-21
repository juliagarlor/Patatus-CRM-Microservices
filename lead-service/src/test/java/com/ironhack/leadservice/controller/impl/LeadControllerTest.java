package com.ironhack.leadservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.leadservice.model.*;
import com.ironhack.leadservice.repository.*;
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
class LeadControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private LeadRepository leadRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Lead lead1 = new Lead("Cayetano", "925959295", "cayetano@gmail.com",
                "cayetano SL", 1l);
        Lead lead2 = new Lead("Cayetana", "925959299", "cayetana@gmail.com",
                "chupiguay INC", 1l);
        Lead lead3 = new Lead("Maria Del Carmen", "925959296", "mdelcarmen@gmail.com",
                "cayetano SL", 2l);

        leadRepository.saveAll(List.of(lead1, lead2, lead3));
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
    }

//    TODO: por alguna razon esto me da problemas al guardar los leads en el repositorio
    @Test
    void findLeadCountBySalesRep() throws Exception {
        MvcResult result = mockMvc.perform(get("/leads/count/bysalesrep")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("1: 2"));
        assertTrue(result.getResponse().getContentAsString().contains("2: 1"));
    }
}