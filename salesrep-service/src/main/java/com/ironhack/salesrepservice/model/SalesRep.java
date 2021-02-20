package com.ironhack.salesrepservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

//    @OneToMany(mappedBy = "repLead")
//    private List<Lead> repLead;
//    @OneToMany(mappedBy = "repOpportunity")
//    private List<Opportunity> repOpportunity;

    //Constructors
    public SalesRep() {
    }



    public SalesRep(String name) {
        setName(name);
    }

    //getters and setters:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
