package com.ironhack.contactservice.service.impl;

import com.ironhack.contactservice.client.LeadClient;
import com.ironhack.contactservice.controller.dto.AccountIdDTO;
import com.ironhack.contactservice.controller.dto.ContactDTO;
import com.ironhack.contactservice.controller.dto.LeadDTO;
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
    @Autowired
    private LeadClient leadClient;


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
    public Contact createContact(int id) {

        //Get a leadDTO from microservice lead, to create a new contact with the data
        LeadDTO leadDTO = leadClient.getLeadDTOById(id);

        Contact contact = new Contact(leadDTO.getName(),leadDTO.getPhoneNumber(),leadDTO.getEmail(),leadDTO.getCompanyName());

        return contactRepository.save(contact);
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
