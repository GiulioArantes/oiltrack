package com.arantes.oiltrack.dto.productPrice;

import com.arantes.oiltrack.models.ProductPrice;

import java.math.BigDecimal;

public record ProductPriceDTO(Long id, String description, BigDecimal value) {

    public ProductPriceDTO(ProductPrice productPrice) {
        this(productPrice.getId(), productPrice.getDescription(), productPrice.getValue());
    }
}
