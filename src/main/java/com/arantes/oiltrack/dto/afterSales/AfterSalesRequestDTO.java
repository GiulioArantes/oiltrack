package com.arantes.oiltrack.dto.afterSales;

import java.time.LocalDate;

public record AfterSalesRequestDTO(LocalDate date, String description, String type, Long customerId) {
}
