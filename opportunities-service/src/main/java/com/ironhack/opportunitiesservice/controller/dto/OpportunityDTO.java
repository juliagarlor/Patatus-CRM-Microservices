package com.ironhack.opportunitiesservice.controller.dto;

import com.ironhack.opportunitiesservice.enums.Product;
import com.ironhack.opportunitiesservice.enums.Status;

public class OpportunityDTO {

    private Long id;
    private int quantity;
    private Long decisionMakerId;
    private Status status;
    private Product product;

    private Long repOpportunityId;
    private @javax.validation.constraints.NotNull @javax.validation.constraints.NotEmpty Long accountId;

    public OpportunityDTO() {
    }

    public OpportunityDTO(Long id, int quantity, Long decisionMakerId, Status status, Product product, Long repOpportunityId, @javax.validation.constraints.NotNull @javax.validation.constraints.NotEmpty Long accountId) {
        setId(id);
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setStatus(status);
        setProduct(product);
        setRepOpportunityId(repOpportunityId);
        setAccountId(accountId);
    }

    public OpportunityDTO(int quantity, Long decisionMakerId, Status status, Product product, Long repOpportunityId) {
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setStatus(status);
        setProduct(product);
        setRepOpportunityId(repOpportunityId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getDecisionMakerId() {
        return decisionMakerId;
    }

    public void setDecisionMakerId(Long decisionMakerId) {
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

    public Long getRepOpportunityId() {
        return repOpportunityId;
    }

    public void setRepOpportunityId(Long repOpportunityId) {
        this.repOpportunityId = repOpportunityId;
    }

    public @javax.validation.constraints.NotNull @javax.validation.constraints.NotEmpty Long getAccountId() {
        return accountId;
    }

    public void setAccountId(@javax.validation.constraints.NotNull @javax.validation.constraints.NotEmpty Long accountId) {
        this.accountId = accountId;
    }
}
