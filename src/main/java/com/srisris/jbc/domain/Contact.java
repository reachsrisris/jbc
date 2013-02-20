package com.srisris.jbc.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.srisris.jbc.dao.DomainObject;

@Entity
@Table(name = "CONTACTS")
public class Contact implements DomainObject {

	public static final int MAX_LENGTH_EMAIL_ADDRESS = 100;
	public static final int MAX_LENGTH_FIRST_NAME = 50;
	public static final int MAX_LENGTH_LAST_NAME = 100;
	public static final int MAX_LENGTH_PHONE_NUMBER = 30;

	@Id
	@GeneratedValue
	@Column(name = "CONTACT_ID")
	private Long id;

	@Column(name = "email_address", length = MAX_LENGTH_EMAIL_ADDRESS)
	private String emailAddress;

	@Column(name = "first_name", nullable = false, length = MAX_LENGTH_FIRST_NAME)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = MAX_LENGTH_LAST_NAME)
	private String lastName;

	@Column(name = "phone_number", length = MAX_LENGTH_PHONE_NUMBER)
	private String phoneNumber;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "CONTACTS_ADDRESS", joinColumns = { @JoinColumn(name = "CONTACT_ID") }, inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID") })
	private Set<Address> addresses = new HashSet<Address>();

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getEmailAddress() {
		return emailAddress;
	}

	public final void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final String getPhoneNumber() {
		return phoneNumber;
	}

	public final void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public final Set<Address> getAddresses() {
		return addresses;
	}

	public final void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
