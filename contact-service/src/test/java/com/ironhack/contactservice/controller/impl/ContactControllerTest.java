package com.ironhack.contactservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.contactservice.model.Contact;
import com.ironhack.contactservice.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactControllerTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Contact> contactList = Arrays.asList(
                new Contact(
                        "Juan Alberto",
                        "665734987",
                        "jherrador@gmail.com",
                        "médicos con fronteras",
                        1L
                ),
                new Contact(
                        "Nacho",
                        "647123123",
                        "nachuatadawi@hotmail.com",
                        "magic&phoenix",
                        2L
                ),
                new Contact(
                        "Brian",
                        "666666666",
                        "brianElJudio@hotmail.com",
                        "oro and Samir friend",
                        3L
                )
        );
        contactRepository.saveAll(contactList);
    }

    @AfterEach
    void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    void getContactDTOById_ParamId_Contact() throws Exception {
        Contact contact = new Contact("Karla", "666222666", "karla@hotmail.com", "oro and Samir friend", 3L);
        MvcResult result = mockMvc.perform(get("/contact/{id}")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Karla"));
    }


    @Test
    void createContact() throws Exception {
        Contact contact = new Contact("Karla", "666222666", "karla@hotmail.com", "oro and Samir friend", 3L);
        String body = objectMapper.writeValueAsString(contact);
        MvcResult result = mockMvc.perform(post("/contact/{id}").content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Karla"));
    }

    @Test
    void updateContactAccountId() throws Exception {
        Contact contact = new Contact("Karla", "666222666", "karla@hotmail.com", "oro and Samir friend", 3L);
        String body = objectMapper.writeValueAsString(contact);
        mockMvc.perform(MockMvcRequestBuilders.put("/contact/{id}/account-id", 1)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Karla"));    }
}