package com.arantes.oiltrack.dto.sale;

import com.arantes.oiltrack.models.Sale;
import com.arantes.oiltrack.models.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleResponseDTO(Long id, LocalDate dateSale, String description, BigDecimal totalValue,
                              SaleStatus saleStatus, String observation) {

    public SaleResponseDTO(Sale sale) {
        this(sale.getId(), sale.getDateSale(), sale.getDescription(), sale.getTotalValue(),
                sale.getSaleStatus(), sale.getObservation());
    }
}
