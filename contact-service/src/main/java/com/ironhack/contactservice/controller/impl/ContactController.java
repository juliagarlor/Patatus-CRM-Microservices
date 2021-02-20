package com.ironhack.contactservice.controller.impl;

import com.ironhack.contactservice.controller.dto.AccountIdDTO;
import com.ironhack.contactservice.controller.dto.ContactDTO;
import com.ironhack.contactservice.controller.dto.LeadDTO;
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

    @GetMapping("/contact/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO getContactDTOById(@PathVariable int id) {
        return contactService.getContactById(id);
    }

    //===========================================
    //Post methods
    //===========================================

    @PostMapping("/contact/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@PathVariable int id) {
        return contactService.createContact(id);
    }

    //===========================================
    //Patch methods
    //===========================================

    @PatchMapping("/contact/{id}/account-id")
    public void updateContactAccountId(@PathVariable int id, @RequestBody @Valid AccountIdDTO accountIdDTO) {
        contactService.updateContact(id,accountIdDTO);
    }
}
