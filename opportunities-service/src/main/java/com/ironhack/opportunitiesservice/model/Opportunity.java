package com.ironhack.opportunitiesservice.model;



import com.ironhack.opportunitiesservice.enums.Product;
import com.ironhack.opportunitiesservice.enums.Status;

import javax.persistence.*;

@Entity
public class Opportunity {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Long decisionMakerId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Product product;

    private Long repOpportunityId;
    private Long accountId;

    // Constructors:
    public Opportunity() {
    }

    public Opportunity(int quantity, Long decisionMakerId, Status status, Product product, Long repOpportunityId, @javax.validation.constraints.NotNull @javax.validation.constraints.NotEmpty Long accountId) {
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setStatus(status);
        setProduct(product);
        setRepOpportunityId(repOpportunityId);
        setAccountId(accountId);
    }

    public Opportunity(int quantity, Long decisionMakerId, Status status, Product product, Long repOpportunityId) {
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}


