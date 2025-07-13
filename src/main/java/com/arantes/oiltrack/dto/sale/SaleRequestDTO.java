package com.arantes.oiltrack.dto.sale;

import com.arantes.oiltrack.models.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleRequestDTO(LocalDate dateSale, String description, BigDecimal totalValue,
                             SaleStatus saleStatus, String observation) {
}
