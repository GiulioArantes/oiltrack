package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.visit.VisitRequestDTO;
import jakarta.persistence.*;
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
    private LocalDate visitDate;

    @Setter
    @Size(max = 500)
    private String description;

    public Visit(VisitRequestDTO data) {
        this.visitDate = data.visitDate();
        this.description = data.description();
    }
}
