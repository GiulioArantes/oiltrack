package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.customer.CustomerRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "name" })
@EqualsAndHashCode(of = { "id" })
@Table(name = "tb_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank(message = "The attribute is required")
    @Size(max = 100, message = "The attribute can contain a maximum of 100 characters")
    private String name;

    @Setter
    @Size(max = 150)
    private String corporateReason;

    @Setter
    @NotBlank
    @Size(max = 18, message = "The CNPJ can contain a maximum of 18 characters")
    private String cnpj;

    @Setter
    @Size(max = 20)
    private String phone;

    @Setter
    @Size(max = 100)
    private String mail;

    @Setter
    @Size(max = 100)
    private String address;

    public Customer(CustomerRequestDTO data) {
        this.name = data.name();
        this.corporateReason = data.corporateReason();
        this.cnpj = data.cnpj();
        this.phone = data.phone();
        this.mail = data.mail();
        this.address = data.address();
    }
}
