package com.arantes.oiltrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arantes.oiltrack.models.Costomer;

public interface CostomerRepository extends JpaRepository<Costomer, Long> {

}
