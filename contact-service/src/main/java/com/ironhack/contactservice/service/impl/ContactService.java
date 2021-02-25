package com.ironhack.contactservice.service.impl;

import com.ironhack.contactservice.client.AccountClient;
import com.ironhack.contactservice.client.LeadClient;
import com.ironhack.contactservice.controller.dto.AccountIdDTO;
import com.ironhack.contactservice.controller.dto.ContactDTO;
import com.ironhack.contactservice.controller.dto.LeadDTO;
import com.ironhack.contactservice.model.Contact;
import com.ironhack.contactservice.repository.ContactRepository;
import com.ironhack.contactservice.service.interfaces.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private LeadClient leadClient;
    @Autowired
    private AccountClient accountClient;


    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    //===========================================
    //Get methods
    //===========================================
    public ContactDTO getContactById(Long id) {

        if(contactRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with id " + id + " not found");
        }
        Contact contact = contactRepository.findById(id).get();
        return new ContactDTO(
                contact.getId(),
                contact.getName(),
                contact.getPhoneNumber(),
                contact.getEmail(),
                contact.getCompanyName(),
                contact.getAccountId()
        );
    }
    //===========================================
    //Post methods
    //===========================================
    public Contact createContact(Long leadId, Long accountId) {

        CircuitBreaker leadCircuitBreaker = circuitBreakerFactory.create("leadService-dev");
        CircuitBreaker accountCircuitBreaker = circuitBreakerFactory.create("accountService-dev");

        Optional<LeadDTO> leadDTO = leadCircuitBreaker.run(() -> leadClient.getLeadDTOById(leadId), throwable -> leadFallback());

        //Get a leadDTO from microservice lead, to create a new contact with the data
        if(Optional.of(leadClient.getLeadDTOById(leadId)).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead with id " + leadId + " not found");
        }else if (accountClient.getAccountId(accountId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + accountId + " not found");
        }
        }
        accountCircuitBreaker.run(() -> accountClient.getAccountId(accountId), throwable -> accountFallback());

        LeadDTO leadDTO = leadClient.getLeadDTOById(leadId);

        Contact contact = new Contact(leadDTO.getName(),leadDTO.getPhoneNumber(),leadDTO.getEmail(),leadDTO.getCompanyName(), accountId);

        leadClient.deleteLead(leadId);

        return contactRepository.save(contact);
    }
    //===========================================
    //Patch methods
    //===========================================
    public void updateContact(Long id, AccountIdDTO accountIdDTO) {

        if(contactRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with id " + id + " not found");
        }
        Contact contact = contactRepository.findById(id).get();
        contact.setAccountId(accountIdDTO.getAccountId());
        contactRepository.save(contact);
    }



    private Long accountFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Account Service is down...");
    }

    private Optional<LeadDTO> leadFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Lead Service is down...");
    }
}
