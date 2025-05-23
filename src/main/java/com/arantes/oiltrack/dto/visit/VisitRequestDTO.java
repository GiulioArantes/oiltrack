package com.arantes.oiltrack.dto.visit;

import java.time.LocalDate;

public record VisitRequestDTO(LocalDate visitDate, String description) {
}
