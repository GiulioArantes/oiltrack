package com.arantes.oiltrack.dto.saleItem;

import com.arantes.oiltrack.models.Product;

public record SaleItemRequestDTO(Long productId, Integer quantity, Double price) {
}
