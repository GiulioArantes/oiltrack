package com.arantes.oiltrack.dto.contact;

public record ContactRequestDTO(String name,
                                String sector,
                                String landline,
                                String extension,
                                String phone,
                                Long customerId) {
}
