package com.ironhack.opportunitiesservice.controller.dto;

public class SalesRepDTO {

    private int id;
    private String name;

    public SalesRepDTO() {
    }

    public SalesRepDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SalesRepDTO(String name) {
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
