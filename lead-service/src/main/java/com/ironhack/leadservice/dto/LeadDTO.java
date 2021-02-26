package com.ironhack.leadservice.dto;


import com.ironhack.leadservice.model.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LeadDTO {

    // Properties
    private Long id;
    @NotEmpty(message = "Name can't be empty.")
    private String name;
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$", message = "Insert a valid Phone number.")
    private String phoneNumber;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Insert a valid Email.")
    private String email;
    @NotEmpty(message = "Company name can't be empty.")
    private String companyName;
    @NotNull(message = "Salesrep ID can't be null.")
    private Long salesrepId;

    // Constructors
    public LeadDTO() {
    }
    public LeadDTO(@NotEmpty(message = "Name can't be empty.") String name, @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$", message = "Insert a valid Phone number.") String phoneNumber, @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Insert a valid Email.") String email, @NotEmpty(message = "Company name can't be empty.") String companyName, @NotNull(message = "Salesrep ID can't be null.") Long salesrepId) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setSalesrepId(salesrepId);
    }
    public LeadDTO(Long id, @NotEmpty(message = "Name can't be empty.") String name, @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$", message = "Insert a valid Phone number.") String phoneNumber, @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Insert a valid Email.") String email, @NotEmpty(message = "Company name can't be empty.") String companyName, @NotNull(message = "Salesrep ID can't be null.") Long salesrepId) {
        this(name, phoneNumber, email, companyName, salesrepId);
        setId(id);
    }

    public LeadDTO(Lead lead){
        this(lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName(), lead.getSalesrepId());
    }

    // Getters & Setters
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getSalesrepId() {
        return salesrepId;
    }

    public void setSalesrepId(Long salesrepId) {
        this.salesrepId = salesrepId;
    }
}
