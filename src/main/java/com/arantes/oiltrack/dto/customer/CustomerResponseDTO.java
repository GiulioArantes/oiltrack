package com.arantes.oiltrack.dto.customer;

import com.arantes.oiltrack.models.Customer;

public record CustomerResponseDTO(Long id, String name, String corporateReason, String cnpj, String phone, String mail,
                                  String address) {

    public CustomerResponseDTO(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getCorporateReason(), customer.getCnpj(),
                customer.getPhone(), customer.getMail(), customer.getAddress());
    }
}
