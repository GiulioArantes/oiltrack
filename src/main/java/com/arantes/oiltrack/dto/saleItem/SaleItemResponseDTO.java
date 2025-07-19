package com.arantes.oiltrack.dto.saleItem;

import com.arantes.oiltrack.models.SaleItem;

public record SaleItemResponseDTO(Long SaleId ,Long productId, Integer quantity, Double price, Double subTotal) {

    public SaleItemResponseDTO(SaleItem saleItem) {
        this(saleItem.getSale().getId(), saleItem.getProduct().getId(), saleItem.getQuantity(),
                saleItem.getPrice(), saleItem.getSubTotal());
    }
}
