package com.ironhack.contactservice.service.interfaces;

import com.ironhack.contactservice.controller.dto.AccountIdDTO;
import com.ironhack.contactservice.controller.dto.ContactDTO;
import com.ironhack.contactservice.model.Contact;

public interface IContactService {

    //Get Method: get a contactDTO by id
    ContactDTO getContactById(Long id);
    //Post Method: create a contactDTO
    Contact createContact (Long id);
    //Patch Method: update a Contact account_id
    void updateContact(Long id, AccountIdDTO accountIdDTO);


}
