package com.ironhack.salesrepservice.controller.dto;

import com.ironhack.salesrepservice.enums.Product;
import com.ironhack.salesrepservice.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class OpportunityDTO {

    @NotNull
    @NotEmpty
    private Long id;
    @NotNull
    @NotEmpty
    private int quantity;
    @NotNull
    @NotEmpty
    private ContactDTO decisionMaker;
    @Enumerated(EnumType.STRING)
    @NotNull
    @NotEmpty
    private Status status;
    @Enumerated(EnumType.STRING)
    @NotNull
    @NotEmpty
    private Product product;

    public OpportunityDTO() {
    }

    public OpportunityDTO(@NotNull @NotEmpty Long id, @NotNull @NotEmpty int quantity, @NotNull @NotEmpty ContactDTO decisionMaker, @NotNull @NotEmpty Status status, @NotNull @NotEmpty Product product) {
        setId(id);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        setStatus(status);
        setProduct(product);
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

    public ContactDTO getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(ContactDTO decisionMaker) {
        this.decisionMaker = decisionMaker;
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
}
