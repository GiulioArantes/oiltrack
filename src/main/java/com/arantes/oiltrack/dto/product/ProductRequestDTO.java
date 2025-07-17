package com.arantes.oiltrack.dto.product;

import com.arantes.oiltrack.dto.productPrice.ProductPriceDTO;

import java.util.List;

public record ProductRequestDTO(String name, String description, List<ProductPriceDTO> prices) {
}
