package com.arantes.oiltrack.dto.category;

import com.arantes.oiltrack.models.Category;
import com.arantes.oiltrack.models.Product;

import java.util.List;

public record CategoryResponseDTO(Long id, String name, List<String> products) {

    public CategoryResponseDTO(Category category) {
        this(category.getId(),
                category.getName(),
                category.getProducts().stream().map(Product::getName).toList());
    }
}
