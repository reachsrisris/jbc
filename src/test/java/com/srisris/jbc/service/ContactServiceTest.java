package com.srisris.jbc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.srisris.jbc.conf.JbcTestApplicationContext;
import com.srisris.jbc.domain.Contact;
import com.srisris.jbc.dto.ContactDTO;
import com.srisris.jbc.repository.ContactRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JbcTestApplicationContext.class })
public class ContactServiceTest {

	@Autowired
	ContactService service;

	@Test
	public void testSave() {
		/*
		 * Contact.Builder contactBuilder = Contact.getBuilder("John", "Doe");
		 * contactBuilder.emailAddress("john.doe@idontknow.com");
		 * contactBuilder.address("11635 Charter Oak Ct", "20190", "Reston",
		 * "VA", "Fairfax");
		 * 
		 * Contact contact = contactBuilder.build();
		 * 
		 * //Contact c = repository.saveAndFlush(contact);
		 */

		ContactDTO contactDTO = new ContactDTO();

		contactDTO.setFirstName("firstName");
		contactDTO.setLastName("lastName");
		contactDTO.setEmailAddress("emailAddress@gmail.com");
		contactDTO.setPhoneNumber("1234567890");
		contactDTO.setStreetAddress("11629 Charter");
		contactDTO.setState("VA");
		contactDTO.setCountry("USA");
		contactDTO.setPostCode("20190");
		contactDTO.setPostOffice("Reston");
		Contact c = service.add(contactDTO);

		System.out.println("Contact " + c.getEmailAddress());
	}

	@Test
	public void testUpdate() throws Exception {
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setId(new Long(2));
		contactDTO.setFirstName("John");
		contactDTO.setLastName("lastName");
		contactDTO.setEmailAddress("emailAddress@gmail.com");
		contactDTO.setPhoneNumber("1234567890");
		contactDTO.setStreetAddress("11629 Charter");
		contactDTO.setState("VA");
		contactDTO.setCountry("USA");
		contactDTO.setPostCode("20190");
		contactDTO.setPostOffice("PReston");
		Contact c = service.update(contactDTO);

		System.out.println("Contact " + c.getEmailAddress());
	}

}
