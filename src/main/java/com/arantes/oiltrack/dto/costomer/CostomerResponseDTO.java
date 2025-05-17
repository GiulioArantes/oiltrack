package com.arantes.oiltrack.dto.costomer;

import com.arantes.oiltrack.models.Costomer;

public record CostomerResponseDTO(Long id, String name, String corporateReason, String cnpj, String phone, String mail,
        String address) {

    public CostomerResponseDTO(Costomer costomer) {
        this(costomer.getId(), costomer.getName(), costomer.getCorporateReason(), costomer.getCnpj(),
                costomer.getPhone(), costomer.getMail(), costomer.getAddress());
    }
}
