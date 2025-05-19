package com.arantes.oiltrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arantes.oiltrack.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
