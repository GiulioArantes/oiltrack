package com.arantes.oiltrack.dto.visit;

import com.arantes.oiltrack.models.Visit;

import java.time.LocalDate;

public record VisitResponseDTO(Long id, LocalDate visitDate, String description) {

    public VisitResponseDTO(Visit visit) {
        this(visit.getId(), visit.getVisitDate(), visit.getDescription());
    }

}
