package com.srisris.jbc.dao.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.srisris.jbc.dao.ContactDao;
import com.srisris.jbc.domain.Contact;

//@Repository("contactDao")
public class ContactDaoImpl{// extends GenericDaoImpl<Contact> implements ContactDao {

//
//
//  public ContactDaoImpl() {
//    super(Contact.class);
//  }
//
//  @SuppressWarnings("unchecked")
//  @Override
//  public List<Contact> findByFirstName(String firstName) {
//    List<Contact> results = null;
//    Query query = entityManager.createQuery("from Contact as c where c.firstName = :firstName");
//    query.setParameter("firstName", firstName);
//    results = query.getResultList();
//    if (results == null || results.size() <= 0) {
//      throw new EntityNotFoundException(firstName + " not found");
//    } else {
//      return results;
//    }
//  }
//
//  @SuppressWarnings("unchecked")
//  @Override
//  public List<Contact> findByLastName(String lastName) {
//    List<Contact> results = null;
//    Query query = entityManager.createQuery("from Contact as c where c.lastName = :lastName");
//    query.setParameter("lastName", lastName);
//    results = query.getResultList();
//    if (results == null || results.size() <= 0) {
//      throw new EntityNotFoundException(lastName + " not found");
//    } else {
//      return results;
//    }
//  }
//
//  @SuppressWarnings("unchecked")
//  @Override
//  public List<Contact> findByEmailAddress(String emailAddress) {
//    List<Contact> results = null;
//    Query query =
//        entityManager.createQuery("from Contact as c where c.emailAddress = :emailAddress");
//    query.setParameter("emailAddress", emailAddress);
//    results = query.getResultList();
//    if (results == null || results.size() <= 0) {
//      throw new EntityNotFoundException(emailAddress + " not found");
//    } else {
//      return results;
//    }
//  }

}
