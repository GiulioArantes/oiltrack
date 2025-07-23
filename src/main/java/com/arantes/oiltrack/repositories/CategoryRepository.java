package com.arantes.oiltrack.repositories;

import com.arantes.oiltrack.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
