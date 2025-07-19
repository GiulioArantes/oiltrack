package com.arantes.oiltrack.dto.contact;

import com.arantes.oiltrack.models.Contact;

public record ContactResponseDTO(Long id,
                                 String name,
                                 String sector,
                                 String landline,
                                 String extension,
                                 String phone,
                                 String customerName) {

    public ContactResponseDTO(Contact contact) {
        this(contact.getId(),
                contact.getName(),
                contact.getSector(),
                contact.getLandline(),
                contact.getExtension(),
                contact.getPhone(),
                contact.getCustomer().getName());
    }
}
