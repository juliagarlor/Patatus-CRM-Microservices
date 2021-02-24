package com.ironhack.edgeservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("contactService-dev")
public interface ContactClient {
}
