package com.ironhack.opportunitiesservice.controller.dto;

import com.ironhack.opportunitiesservice.enums.Product;
import com.ironhack.opportunitiesservice.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class OpportunityDTO {

    private int id;
    private int quantity;
    private int decisionMakerId;
    private Status status;
    private Product product;

    private int repOpportunityId;
    private int accountId;

    public OpportunityDTO() {
    }

    public OpportunityDTO(int id, int quantity, int decisionMakerId, Status status, Product product, int repOpportunityId, int accountId) {
        setId(id);
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setStatus(status);
        setProduct(product);
        setRepOpportunityId(repOpportunityId);
        setAccountId(accountId);
    }

    public OpportunityDTO(int quantity, int decisionMakerId, Status status, Product product, int repOpportunityId) {
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setStatus(status);
        setProduct(product);
        setRepOpportunityId(repOpportunityId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDecisionMakerId() {
        return decisionMakerId;
    }

    public void setDecisionMakerId(int decisionMakerId) {
        this.decisionMakerId = decisionMakerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRepOpportunityId() {
        return repOpportunityId;
    }

    public void setRepOpportunityId(int repOpportunityId) {
        this.repOpportunityId = repOpportunityId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
