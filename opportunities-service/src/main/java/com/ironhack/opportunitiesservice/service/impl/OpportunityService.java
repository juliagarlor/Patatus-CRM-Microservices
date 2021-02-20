package com.ironhack.opportunitiesservice.service.impl;

import com.ironhack.opportunitiesservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.math.*;
import java.util.*;

@Service
public class OpportunityService {
    @Autowired
    private OpportunityRepository opportunityRepository;

    public BigDecimal getMean(String data) {
        switch (data.toLowerCase()){
            case "quantity":
                return opportunityRepository.findAverageQuantityFromOpportunities();
            case "opportunities":
                BigDecimal output = opportunityRepository.findAvgOpportunitiesByAccount();

                if (output == null){
                    return BigDecimal.ZERO;
                }
                return output;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "quantity or opportunities");
        }
    }

    public List<Object[]> getMaxOpportunities() {
        List<Object[]> output = opportunityRepository.findMaxOpportunitiesByAccount();

        if (output.size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
        }
        return output;
    }

    public int getMaxQuantity(){
        return opportunityRepository.findMaxQuantityFromOpportunities();
    }

    public List<Object[]> getMinOpportunities() {
        List<Object[]> output = opportunityRepository.findMinOpportunitiesByAccount();

        if (output.size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
        }
        return output;
    }

    public int getMinQuantity(){
        return opportunityRepository.findMinQuantityFromOpportunities();
    }

    public double getMedian(String data) {
        switch (data.toLowerCase()){
            case "quantity":
                List<Integer[]> orderedList = opportunityRepository.orderOpportunities();

                if (orderedList.size() == 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
                }

                return findMedian(orderedList);
            case "opportunities":
                orderedList = opportunityRepository.findOpportunitiesByAccountOrdered();

                if (orderedList.size() == 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunities yet.");
                }

                return findMedian(orderedList);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, introduce a valid data parameter: " +
                        "quantity or opportunities");
        }
    }

    // List MUST de previously ordered
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
