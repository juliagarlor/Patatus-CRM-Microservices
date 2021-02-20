package com.ironhack.opportunitiesservice.client;

import com.ironhack.opportunitiesservice.controller.dto.CityDTO;
import com.ironhack.opportunitiesservice.controller.dto.CountryDTO;
import com.ironhack.opportunitiesservice.controller.dto.IndustryDTO;
import com.ironhack.opportunitiesservice.enums.Industry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("accountService-dev")
public interface AccountClient {

    //This method return an acount
//    @GetMapping("/account/{accountId}")
//    AccountDTO getAccountById(@PathVariable int accountId);
    //TODO: metodos que devuelvan un id y un country, un id y un pais y un id y una industria
    @GetMapping("/accounts")
    List<CountryDTO> getAccountByCountry(String country);
    @GetMapping("/accounts")
    List<CityDTO> getAccountByCity(String city);
    @GetMapping("/accounts")
    List<IndustryDTO> getAccountByIndustry(Industry industry);

}
