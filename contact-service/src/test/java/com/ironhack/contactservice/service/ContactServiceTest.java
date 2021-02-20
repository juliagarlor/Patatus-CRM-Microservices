package com.ironhack.contactservice.service;

import com.ironhack.contactservice.ContactServiceApplication;
import com.ironhack.contactservice.client.LeadClient;
import com.ironhack.contactservice.model.Contact;
import com.ironhack.contactservice.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWi
@SpringBootTest(classes = ContactServiceApplication.class)
class ContactServiceTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private LeadClient leadClient;

    @BeforeEach
    void setUp() {
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
    void getContactById_ContactId_ContactDTO() {
        Optional<Contact> contact = contactRepository.findByAccountId(2L);
        assertTrue(contact.isPresent());
        assertEquals("Nacho", contact.get().getName());

    }

    @Test
    void createContact_TakingLeads_ConvertsContact() {
        Contact contact = new Contact("Susan Honum","8765433345","susan98@gmaiil.com","Camino a casa");
        contactRepository.save(contact);
        assertEquals("Susan Honum", contact.getName());
    }

    @Test
    void updateContact() {
        Optional<Contact> contact = contactRepository.findByAccountId(2L);
        contact.get().setEmail("prueba@gmail.com");
        contactRepository.save(contact.get());
        assertEquals("prueba@gmail.com", contact.get().getEmail());
    }
}