package com.ironhack.statsservice.controller.interfaces;

import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

public interface IStatsController {
    BigDecimal getMean(String data);
    List<Object[]> getMax(String data);
    List<Object[]> getMin(String data);
    double getMedian(String data);
}
