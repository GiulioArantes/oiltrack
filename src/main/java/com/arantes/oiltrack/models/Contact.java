package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.contact.ContactRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Size(max = 150)
    private String name;

    @Setter
    @Size(max = 150)
    private String sector;

    @Setter
    @Size(max = 20)
    private String landline;

    @Setter
    @Size(max = 150)
    private String extension;

    @Setter
    @Size(max = 20)
    private String phone;

    public Contact(ContactRequestDTO data) {
        this.name = data.name();
        this.sector = data.sector();
        this.landline = data.landline();
        this.extension = data.extension();
        this.phone = data.phone();
    }

}
