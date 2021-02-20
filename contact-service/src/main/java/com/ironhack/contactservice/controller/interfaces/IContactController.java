package com.ironhack.contactservice.controller.interfaces;

import com.ironhack.contactservice.controller.dto.AccountIdDTO;
import com.ironhack.contactservice.controller.dto.ContactDTO;
import com.ironhack.contactservice.controller.dto.LeadDTO;
import com.ironhack.contactservice.model.Contact;

public interface IContactController {

    //Get method: get a contact by id
    ContactDTO getContactDTOById(int id);
    //Post method: create a contact
    Contact createContact(int id);
    //Patch method: update a contact account_id
    void updateContactAccountId(int id, AccountIdDTO accountIdDTO);
}
