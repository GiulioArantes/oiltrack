package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.customer.CustomerRequestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("customer")
    private List<Visit> visits = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("customer")
    private List<Sale> sales = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("customer")
    private List<AfterSales> afterSales = new ArrayList<>();

    public Customer(CustomerRequestDTO data) {
        this.name = data.name();
        this.corporateReason = data.corporateReason();
        this.cnpj = data.cnpj();
        this.phone = data.phone();
        this.mail = data.mail();
        this.address = data.address();
    }
}
