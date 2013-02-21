package com.srisris.jbc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srisris.jbc.domain.Contact;
import com.srisris.jbc.dto.ContactDTO;
import com.srisris.jbc.repository.ContactRepository;
import com.srisris.jbc.service.ContactService;

@Service
public class RepositoryContactService implements ContactService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryContactService.class);

  @Resource
  private ContactRepository repository;

  @Transactional
  @Override
  public Contact add(ContactDTO added) {
    LOGGER.debug("Adding new contact with information: {}", added);

    // Creates an instance of a Contact by using the builder pattern
    Contact contact =
        Contact
            .getBuilder(added.getFirstName(), added.getLastName())
            .address(added.getStreetAddress(), added.getPostCode(), added.getPostOffice(),
                added.getState(), added.getCountry()).emailAddress(added.getEmailAddress())
            .phoneNumber(added.getPhoneNumber()).build();

    return repository.save(contact);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Contact deletedById(Long id) throws Exception {
    LOGGER.debug("Deleting contact by id: {}", id);

    Contact deleted = findById(id);
    repository.delete(deleted);

    LOGGER.debug("Deleted contact: {}", deleted);

    return deleted;
  }

  @Transactional(readOnly = true)
  @Override
  public List<Contact> findAll() {
    LOGGER.debug("Finding all contacts");
    return repository.findAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Contact findById(Long id) throws Exception {
    LOGGER.debug("Finding contact by id: {}", id);

    Contact found = repository.findOne(id);

    if (found == null) {
      LOGGER.debug("No contact found with id: {}", id);
      throw new Exception("No contact found with id: " + id);
    }

    LOGGER.debug("Found contact: {}", found);

    return found;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Contact update(ContactDTO updated) throws Exception {
    LOGGER.debug("Updating contact with information: {}", updated);

    Contact found = findById(updated.getId());

    // Update the contact information
    found.update(updated.getFirstName(), updated.getLastName(), updated.getEmailAddress(),
        updated.getPhoneNumber());
    // Update the address information
    found.updateAddress(updated.getStreetAddress(), updated.getPostCode(), updated.getPostOffice(),
        updated.getState(), updated.getCountry());

    return found;//repository.saveAndFlush(found);
  }

}
