package com.arantes.oiltrack.dto.customer;

import com.arantes.oiltrack.dto.visit.VisitResponseDTO;
import com.arantes.oiltrack.models.Customer;

import java.util.List;

public record CustomerResponseDTO(Long id, String name, String corporateReason, String cnpj, String phone, String mail,
                                  String address, List<VisitResponseDTO> visits) {

    public CustomerResponseDTO(Customer customer) {
        this(customer.getId(),
                customer.getName(),
                customer.getCorporateReason(),
                customer.getCnpj(),
                customer.getPhone(),
                customer.getMail(),
                customer.getAddress(),
                customer.getVisits().stream().map(VisitResponseDTO::new).toList());
    }
}
