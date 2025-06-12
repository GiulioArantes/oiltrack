package com.arantes.oiltrack.dto.product;

import com.arantes.oiltrack.models.Product;

public record ProductResponseDTO(Long id, String name, String description) {

    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription());
    }
}
