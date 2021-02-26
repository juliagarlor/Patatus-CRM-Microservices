package com.ironhack.accountservice.enums;

public enum Industry {
    PRODUCE("Produce"),
    ECOMMERCE("e-Commerce"),
    MANUFACTURING("Manufacturing"),
    MEDICAL("Medical"),
    OTHER("Other");

    private final String name;

    Industry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
