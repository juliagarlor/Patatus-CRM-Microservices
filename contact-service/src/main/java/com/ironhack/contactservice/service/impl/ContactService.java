package com.ironhack.contactservice.service.impl;

import com.ironhack.contactservice.controller.dto.AccountIdDTO;
import com.ironhack.contactservice.controller.dto.ContactDTO;
import com.ironhack.contactservice.model.Contact;
import com.ironhack.contactservice.repository.ContactRepository;
import com.ironhack.contactservice.service.interfaces.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepository contactRepository;


    //===========================================
    //Get methods
    //===========================================
    public ContactDTO getContactById(int id) {

        if(contactRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with id " + id + " not found");
        }
        Contact contact = contactRepository.findById(id).get();
        return new ContactDTO(contact.getId(), contact.getName(), contact.getPhoneNumber(), contact.getEmail(), contact.getCompanyName(),contact.getAccountId());
    }
    //===========================================
    //Post methods
    //===========================================
    public Contact createContact(ContactDTO contactDTO) {

        Contact contact = new Contact(contactDTO.getName(),contactDTO.getPhoneNumber(),contactDTO.getEmail(),contactDTO.getCompanyName());
        contactRepository.save(contact);
        return contact;
    }
    //===========================================
    //Patch methods
    //===========================================
    public void updateContact(int id, AccountIdDTO accountIdDTO) {

        if(contactRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with id " + id + " not found");
        }
        Contact contact = contactRepository.findById(id).get();
        contact.setAccountId(accountIdDTO.getAccountId());
        contactRepository.save(contact);
    }
}
