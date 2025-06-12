package com.arantes.oiltrack.repositories;

import com.arantes.oiltrack.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
