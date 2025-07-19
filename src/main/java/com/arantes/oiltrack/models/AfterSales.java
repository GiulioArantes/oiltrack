package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.afterSales.AfterSalesRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Getter
@ToString
@Entity
@Table(name = "tb_afterSales")
public class AfterSales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull(message = "The attribute date is required")
    private LocalDate date;

    @Setter
    @Size(max = 1000)
    private String description;

    @Setter
    @Size(max = 500)
    private String type;

    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("afterSales")
    private Customer customer;

    public AfterSales(AfterSalesRequestDTO data, Customer customer) {
        this.date = data.date();
        this.description = data.description();
        this.type = data.type();
        this.customer = customer;
    }
}
