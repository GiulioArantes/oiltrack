package com.arantes.oiltrack.repositories;

import com.arantes.oiltrack.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
