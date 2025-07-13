package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.sale.SaleRequestDTO;
import com.arantes.oiltrack.models.enums.SaleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tb_sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private LocalDate dateSale;

    @Setter
    @Size(max = 500)
    private String description;

    @Setter
    private BigDecimal totalValue;

    private Integer saleStatus;

    @Setter
    @Size(max = 500)
    private String observation;

    public Sale(Long id, LocalDate dateSale, String description, BigDecimal totalValue, SaleStatus saleStatus,
                String observation) {
        this.id = id;
        this.dateSale = dateSale;
        this.description = description;
        this.totalValue = totalValue;
        setSaleStatus(saleStatus);
        this.observation = observation;
    }

    public SaleStatus getSaleStatus() {
        return SaleStatus.valueOf(saleStatus);
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        if (saleStatus != null) {
            this.saleStatus = saleStatus.getCode();
        }
    }

    public Sale(SaleRequestDTO data) {
        this.dateSale = data.dateSale();
        this.description = data.description();
        this.totalValue = data.totalValue();
        setSaleStatus(data.saleStatus());
        this.observation = data.observation();
    }

}
