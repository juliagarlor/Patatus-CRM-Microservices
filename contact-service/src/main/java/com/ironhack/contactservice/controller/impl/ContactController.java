package com.ironhack.contactservice.controller.impl;

import com.ironhack.contactservice.controller.dto.AccountIdDTO;
import com.ironhack.contactservice.controller.dto.ContactDTO;
import com.ironhack.contactservice.controller.interfaces.IContactController;
import com.ironhack.contactservice.model.Contact;
import com.ironhack.contactservice.service.interfaces.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ContactController implements IContactController {

    @Autowired
    private IContactService contactService;

    //===========================================
    //Get methods
    //===========================================
    //Get a contact by id
    @GetMapping("/contact/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO getContactDTOById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    //===========================================
    //Post methods
    //===========================================
    //create a contact with the id of a lead
    @PostMapping("/contact/lead/{leadId}/account/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@PathVariable Long leadId, @PathVariable Long accountId) {
        return contactService.createContact(leadId, accountId);
    }

    //===========================================
    //Patch methods
    //===========================================

    @PatchMapping("/contact/{id}/account-id")
    public void updateContactAccountId(@PathVariable Long id, @RequestBody @Valid AccountIdDTO accountIdDTO) {
        contactService.updateContact(id,accountIdDTO);
    }
}
