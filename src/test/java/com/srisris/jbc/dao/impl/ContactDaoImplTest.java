package com.srisris.jbc.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.srisris.jbc.conf.JbcTestApplicationContext;
import com.srisris.jbc.dao.ContactDao;
import com.srisris.jbc.domain.Contact;


@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from AppConfig and TestConfig
@ContextConfiguration(classes = {JbcTestApplicationContext.class})
public class ContactDaoImplTest {
  Logger log = LoggerFactory.getLogger(ContactDaoImplTest.class);

  @Autowired
  private ContactDao contactDao;


  @Transactional
  @Test
  public void test() {

    Contact contact = new Contact();
    //contact.setId(new Long(1));
   // contact.setFirstName("John");
   // contact.setLastName("Doe");
   // contact.setEmailAddress("john.doe@idontknow.com");


    // Address address = new Address();
    // address.setStreetAddress("11635 Charter oak ct");
    // address.setPostOffice("Reston");
    // address.setState("VA");
    // address.setCountry("United States");
    //

  //  contactDao.save(contact);

  }

}
