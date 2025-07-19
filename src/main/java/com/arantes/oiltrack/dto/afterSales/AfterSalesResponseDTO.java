package com.arantes.oiltrack.dto.afterSales;

import com.arantes.oiltrack.models.AfterSales;

import java.time.LocalDate;

public record AfterSalesResponseDTO(Long id,
                                    LocalDate date,
                                    String description,
                                    String type,
                                    String customerName) {

    public AfterSalesResponseDTO(AfterSales afterSales) {
        this(afterSales.getId(),
                afterSales.getDate(),
                afterSales.getDescription(),
                afterSales.getType(),
                afterSales.getCustomer().getName());
    }
}
