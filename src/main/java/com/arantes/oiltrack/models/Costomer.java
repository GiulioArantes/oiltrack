package com.arantes.oiltrack.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_costomers")
public class Costomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The attribute is required")
    @Size(max = 100, message = "The attribute can contain a maximum of 100 characters")
    private String name;

    @Size(max = 150)
    private String corporateReason;

    @NotBlank
    @Size(max = 18, message = "The CNPJ can contain a maximum of 18 characters")
    private String cnpj;

    @Size(max = 20)
    private String phone;

    @Size(max = 100)
    private String mail;

    @Size(max = 100)
    private String address;
}
