package com.arantes.oiltrack.dto.customer;

public record CustomerRequestDTO(String name, String corporateReason, String cnpj, String phone, String mail,
                                 String address) {
}
