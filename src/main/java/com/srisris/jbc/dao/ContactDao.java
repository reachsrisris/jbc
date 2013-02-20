package com.srisris.jbc.dao;

import java.util.List;

import com.srisris.jbc.domain.Contact;

public interface ContactDao extends GenericDao<Contact> {


  List<Contact> findByFirstName(String firstName);

  List<Contact> findByLastName(String lastName);

  // Contact findByFirstAndLastName(String firstName, String lastName);

  List<Contact> findByEmailAddress(String emailAddress);
}
