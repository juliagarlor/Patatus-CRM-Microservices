package com.ironhack.edgeservice.controller.dto;

import com.ironhack.opportunitiesservice.enums.Status;

public class OpportunityStatusDTO {

    private Status status;

    public OpportunityStatusDTO() {
    }

    public OpportunityStatusDTO(Status status) {
        setStatus(status);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
