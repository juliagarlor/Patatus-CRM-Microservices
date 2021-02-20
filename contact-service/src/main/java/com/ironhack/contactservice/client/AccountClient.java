//TODO: esto no va a valer para nada, pero lo dejo por si acaso
//package com.ironhack.contactservice.client;
//
//import com.ironhack.contactservice.controller.dto.AccountIdDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient("accountService-dev")
//public interface AccountClient {
//
//    @GetMapping("account/{accountId}/account-id")
//    AccountIdDTO getAccountId (@PathVariable int accountId, @RequestBody AccountIdDTO accountIdDTO);
//
//
//}
