package com.arantes.oiltrack.dto.product;

import com.arantes.oiltrack.dto.productPrice.ProductPriceDTO;
import com.arantes.oiltrack.models.Product;

import java.util.List;

public record ProductResponseDTO(Long id, String name, String description,
                                 List<ProductPriceDTO> prices) {

    public ProductResponseDTO(Product product) {

        this(product.getId(), product.getName(), product.getDescription(),
                product.getPrices().stream().map(ProductPriceDTO::new).toList());
    }
}
