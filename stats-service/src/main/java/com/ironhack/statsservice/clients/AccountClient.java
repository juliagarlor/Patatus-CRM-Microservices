package com.ironhack.statsservice.clients;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

@FeignClient("accountService-dev")
public interface AccountClient {

    @GetMapping("/stats/mean/employee-count")
    BigDecimal findMeanEmployeeCount();

    @GetMapping("/stats/max/employee-count")
    List<Object[]> findMaxEmployeeCount();

    @GetMapping("/stats/min/employee-count")
    List<Object[]> findMinEmployeeCount();

//    Por qué devolveis aquí un array de integers, no os valdría con List<Integer>?
    @GetMapping("/stats/ordered-list-of-employee-count")
    List<Integer[]> findEmployeesByAccountOrdered();
}
