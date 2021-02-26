package com.ironhack.salesrepservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    //Constructors
    public SalesRep() {
    }



    public SalesRep(String name) {
        setName(name);
    }

    //getters and setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public String toString() {
        return "ID-" + id +
                " | name: " + name;
    }
}
