package com.arantes.oiltrack.dto.sale;

import com.arantes.oiltrack.dto.saleItem.SaleItemResponseDTO;
import com.arantes.oiltrack.models.Sale;
import com.arantes.oiltrack.models.enums.SaleStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record SaleResponseDTO(Long id, LocalDate dateSale, String description, SaleStatus saleStatus,
                              String observation, Set<SaleItemResponseDTO> items, Double total) {

    public SaleResponseDTO(Sale sale) {
        this(sale.getId(), sale.getDateSale(), sale.getDescription(), sale.getSaleStatus(),
                sale.getObservation(), sale.getItems().stream()
                        .map(SaleItemResponseDTO::new).collect(Collectors.toSet()),
                sale.getTotal());
    }
}
