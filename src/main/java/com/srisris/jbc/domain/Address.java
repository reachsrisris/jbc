package com.srisris.jbc.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.srisris.jbc.dao.DomainObject;

/**
 * @author schinthalapudi
 * 
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements DomainObject {
	public static final int MAX_LENGTH_COUNTRY = 20;
	public static final int MAX_LENGTH_STREET_ADDRESS = 150;
	public static final int MAX_LENGTH_POST_CODE = 10;
	public static final int MAX_LENGTH_POST_OFFICE = 40;
	public static final int MAX_LENGTH_STATE = 20;

	@Id
	@GeneratedValue
	@Column(name = "ADDRESS_ID")
	private Long id;

	@Column(name = "country", length = MAX_LENGTH_COUNTRY)
	private String country;

	@Column(name = "street_address", length = MAX_LENGTH_STREET_ADDRESS)
	private String streetAddress;

	@Column(name = "post_code", length = MAX_LENGTH_POST_CODE)
	private String postCode;

	@Column(name = "post_office", length = MAX_LENGTH_POST_OFFICE)
	private String postOffice;

	@Column(name = "state", length = MAX_LENGTH_STATE)
	private String state;
	
	@ManyToMany(mappedBy="addresses")
    private Set<Contact> contacts = new HashSet<Contact>();
   

	public final String getCountry() {
		return country;
	}

	public final void setCountry(String country) {
		this.country = country;
	}

	public final String getStreetAddress() {
		return streetAddress;
	}

	public final void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public final String getPostCode() {
		return postCode;
	}

	public final void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public final String getPostOffice() {
		return postOffice;
	}

	public final void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	public final String getState() {
		return state;
	}

	public final void setState(String state) {
		this.state = state;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Set<Contact> getContacts() {
		return contacts;
	}

	public final void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	

}
