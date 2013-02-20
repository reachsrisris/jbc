package com.srisris.jbc.service;

import java.util.List;

import com.srisris.jbc.domain.Contact;
import com.srisris.jbc.dto.ContactDTO;

public interface ContactService {

	public Contact add(ContactDTO added);

	public Contact deletedById(Long id) throws Exception;

	public List<Contact> findAll();

	public Contact findById(Long id) throws Exception;

	public Contact update(ContactDTO updated) throws Exception;

}
