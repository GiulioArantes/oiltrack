package com.arantes.oiltrack.dto.sale;

import com.arantes.oiltrack.dto.saleItem.SaleItemRequestDTO;
import com.arantes.oiltrack.models.enums.SaleStatus;

import java.time.LocalDate;
import java.util.Set;

public record SaleRequestDTO(LocalDate dateSale, String description, SaleStatus saleStatus,
                             String observation, Set<SaleItemRequestDTO> items, Long customerId) {
}
