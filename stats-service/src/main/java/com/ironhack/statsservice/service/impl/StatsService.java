package com.ironhack.statsservice.service.impl;

import com.ironhack.statsservice.clients.*;
import com.ironhack.statsservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.math.*;
import java.util.*;

@Service
public class StatsService implements IStatsService {
    @Autowired
    private AccountClient accountClient;

//    EmployeeCount, quantity and opportunities

    public BigDecimal getMean(String data) {
        switch (data.toLowerCase()){
            case "employeecount":
                return accountClient.findMeanEmployeeCount();
                break;
            case "quantity":
                break;
            case "opportunities":
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "employeeCount, quantity or opportunities");
        }
    }

    public List<Object[]> getMax(String data) {
        switch (data.toLowerCase()){
            case "employeecount":
                return accountClient.findMaxEmployeeCount();
                break;
            case "quantity":
                break;
            case "opportunities":
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "employeeCount, quantity or opportunities");
        }
    }

    public List<Object[]> getMin(String data) {
        switch (data.toLowerCase()){
            case "employeecount":
                return accountClient.findMinEmployeeCount();
                break;
            case "quantity":
                break;
            case "opportunities":
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "employeeCount, quantity or opportunities");
        }
    }

    public double getMedian(String data) {
        switch (data.toLowerCase()){
            case "employeecount":
                return findMedian(accountClient.findEmployeesByAccountOrdered());
                break;
            case "quantity":
                break;
            case "opportunities":
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "employeeCount, quantity or opportunities");
        }
    }

    // Lis MUST de previously ordered
    public double findMedian(List<Integer[]> objects){
        double median;
        int medianPosition = objects.size()/2;
        if(objects.size() % 2 != 0 ){
            median = objects.get(medianPosition)[0];
        } else {
            double firstHalf = (double) objects.get((objects.size()/2)-1)[0];
            double secondHalf = (double) objects.get(medianPosition)[0];
            median = (firstHalf + secondHalf)/2;
        }
        return median;
    }
}
