package com.srisris.jbc.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.srisris.jbc.conf.JbcTestApplicationContext;
import com.srisris.jbc.domain.Contact;
import com.srisris.jbc.dto.ContactDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JbcTestApplicationContext.class})
public class ContactServiceTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceTest.class);

  @Autowired
  ContactService service;

  // @Test
  public void testSave() {
    ContactDTO contactDTO = new ContactDTO();

    contactDTO.setFirstName("firstName");
    contactDTO.setLastName("lastName");
    contactDTO.setEmailAddress("emailAddress@gmail.com");
    contactDTO.setPhoneNumber("1234567890");
    contactDTO.setStreetAddress("11630 Charter");
    contactDTO.setState("VA");
    contactDTO.setCountry("USA");
    contactDTO.setPostCode("20190");
    contactDTO.setPostOffice("Reston");
    Contact c = service.add(contactDTO);

    LOGGER.debug(" Contact {}", c.toString());
  }

  @Test
  public void testUpdate() throws Exception {
    ContactDTO contactDTO = new ContactDTO();
    contactDTO.setId(new Long(1));
    contactDTO.setFirstName("John");
    contactDTO.setLastName("lastName");
    contactDTO.setEmailAddress("emailAddress1@gmail.com");
    contactDTO.setPhoneNumber("1234567890");
    contactDTO.setStreetAddress("11630 Charter");
    contactDTO.setState("VA");
    contactDTO.setCountry("USA");
    contactDTO.setPostCode("20190");
    contactDTO.setPostOffice("PReston");
    Contact c = service.update(contactDTO);
    
    LOGGER.debug("Update Contact {}", c.toString());
  }

  @Test
  public void testFindById() throws Exception {
    Contact contact = service.findById(new Long(1));
    LOGGER.debug("Contact {}", contact.toString());



  }

  @Test
  public void testFindAll() {
    List<Contact> contactsList = service.findAll();

    for (Contact contact : contactsList) {
      LOGGER.debug("Contact {}", contact.toString());
    }


  }
}
