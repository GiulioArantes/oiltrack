package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.sale.SaleRequestDTO;
import com.arantes.oiltrack.models.enums.SaleStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
@Table(name = "tb_sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull(message = "The attribute dateSale is required")
    private LocalDate dateSale;

    @Setter
    @Size(max = 500)
    private String description;

    private Integer saleStatus;

    @Setter
    @Size(max = 500)
    private String observation;

    @OneToMany(mappedBy = "id.sale", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<SaleItem> items = new HashSet<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("sales")
    private Customer customer;

    public Sale(Long id, LocalDate dateSale, String description, SaleStatus saleStatus,
                String observation) {
        this.id = id;
        this.dateSale = dateSale;
        this.description = description;
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

    public Double getTotal() {
        double sum = 0.0;

        for (SaleItem item : items) {
            sum += item.getSubTotal();
        }

        return sum;
    }

    public Sale(SaleRequestDTO data, Customer customer) {
        this.dateSale = data.dateSale();
        this.description = data.description();
        setSaleStatus(data.saleStatus());
        this.observation = data.observation();
        this.customer = customer;
    }
}
