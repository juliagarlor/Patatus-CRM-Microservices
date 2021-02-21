package com.ironhack.accountservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.accountservice.dto.AccountDTO;
import com.ironhack.accountservice.enums.Industry;
import com.ironhack.accountservice.model.Account;
import com.ironhack.accountservice.repository.AccountRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Account account1 = new Account(Industry.ECOMMERCE, 12, "Madrid", "Spain");
        Account account2 = new Account(Industry.ECOMMERCE, 7, "Madrid", "Spain");
        Account account3 = new Account(Industry.PRODUCE, 134, "Barcelona", "Spain");
        Account account4 = new Account(Industry.MANUFACTURING, 218, "Roma", "Italy");

        accountRepository.saveAll(List.of(account1, account2, account3, account4));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void postAccount_ValidDTO_NewAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO("MEDICAL", 56, "Paris", "France");
        String body = objectMapper.writeValueAsString(accountDTO);
        MvcResult result = mockMvc.perform(
                post("/account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("France"));

        List<Account> accounts = accountRepository.findAll();
        assertEquals(5, accounts.size());
    }

    @Test
    void getAllAccounts_nothing_AllAccounts() throws Exception  {
        MvcResult result = mockMvc.perform(get("/accounts")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12"));
        assertTrue(result.getResponse().getContentAsString().contains("7"));
        assertTrue(result.getResponse().getContentAsString().contains("134"));
        assertTrue(result.getResponse().getContentAsString().contains("218"));
    }

    @Test
    void getAccountById_ValidId_RightAccount() throws Exception  {
        List<Account> accounts = accountRepository.findAll();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(get("/account/"+accounts.get(0).getId())).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12"));
        assertFalse(result.getResponse().getContentAsString().contains("7"));
    }

    @Test
    void getAccountsByCountry_ValidCountry_RightListOfAccounts() throws Exception  {
        List<Account> accounts = accountRepository.findAll();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(get("/accounts/country/Spain")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(accounts.get(0).getId().toString()));
        assertTrue(result.getResponse().getContentAsString().contains(accounts.get(1).getId().toString()));
        assertTrue(result.getResponse().getContentAsString().contains(accounts.get(2).getId().toString()));
    }

    @Test
    void getAccountsByCity_ValidCity_RightListOfAccounts() throws Exception  {
        List<Account> accounts = accountRepository.findAll();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(get("/accounts/city/Roma")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(accounts.get(3).getId().toString()));
    }

    @Test
    void getAccountsByIndustry_ValidIndustry_RightListOfAccounts() throws Exception  {
        List<Account> accounts = accountRepository.findAll();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(get("/accounts/industry/produce")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(accounts.get(2).getId().toString()));
    }

    @Test
    void findMeanEmployeeCount_Nothing_RightMean() throws Exception  {
        MvcResult result = mockMvc.perform(get("/stats/mean/employee-count")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("92.75"));
    }

    @Test
    void findMaxEmployeeCount_Nothing_RightMax() throws Exception  {
        MvcResult result = mockMvc.perform(get("/stats/max/employee-count")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("218"));
    }

    @Test
    void findMinEmployeeCount_Nothing_RightMin() throws Exception  {
        MvcResult result = mockMvc.perform(get("/stats/min/employee-count")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("7"));
    }

    @Test
    void findMedianEmployeeCount_Nothing_RightMean() throws Exception  {
        MvcResult result = mockMvc.perform(get("/stats/median/employee-count")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("73"));
    }
}