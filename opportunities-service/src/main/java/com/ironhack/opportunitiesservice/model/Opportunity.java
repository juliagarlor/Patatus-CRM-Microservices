package com.ironhack.opportunitiesservice.model;



import com.ironhack.opportunitiesservice.enums.Product;
import com.ironhack.opportunitiesservice.enums.Status;

import javax.persistence.*;

@Entity
public class Opportunity {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private int decisionMakerId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Product product;

    private int repOpportunityId;
    private int accountId;

    // Constructors:
    public Opportunity() {
    }

    public Opportunity(int quantity, int decisionMakerId, Status status, Product product, int repOpportunityId, int accountId) {
        setQuantity(quantity);
        setDecisionMakerId(decisionMakerId);
        setStatus(status);
        setProduct(product);
        setRepOpportunityId(repOpportunityId);
        setAccountId(accountId);
    }

    public Opportunity(int quantity, int decisionMakerId, Status status, Product product, int repOpportunityId) {
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


