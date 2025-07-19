package com.arantes.oiltrack.repositories;

import com.arantes.oiltrack.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact ,Long> {
}
