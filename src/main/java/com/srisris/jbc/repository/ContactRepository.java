package com.srisris.jbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srisris.jbc.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
