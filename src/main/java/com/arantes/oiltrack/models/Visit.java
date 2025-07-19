package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.visit.VisitRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "tb_visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull(message = "The attribute visitDate is required")
    private LocalDate visitDate;

    @Setter
    @Size(max = 500)
    private String description;

    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("visits")
    private Customer customer;

    public Visit(VisitRequestDTO data, Customer customer) {
        this.visitDate = data.visitDate();
        this.description = data.description();
        this.customer = customer;
    }
}
