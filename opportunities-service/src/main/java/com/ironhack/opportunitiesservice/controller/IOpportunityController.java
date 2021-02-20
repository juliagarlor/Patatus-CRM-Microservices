package com.ironhack.opportunitiesservice.controller;

import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

public interface IOpportunityController {
    BigDecimal getMeanOpportunities(@PathVariable String data);
    List<Object[]> getMaxOpportunities();
    int getMaxQuantity();
    List<Object[]> getMinOpportunities();
    int getMinQuantity();
    double getMedian(@PathVariable String data);
}
