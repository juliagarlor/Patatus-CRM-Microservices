package com.ironhack.statsservice.service.interfaces;

import java.math.*;
import java.util.*;

public interface IStatsService {
    BigDecimal getMean(String data);
    List<Object[]> getMax(String data);
    List<Object[]> getMin(String data);
    double getMedian(String data);
    double findMedian(List<Integer[]> objects);
}
