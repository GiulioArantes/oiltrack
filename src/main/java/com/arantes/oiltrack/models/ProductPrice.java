package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.productPrice.ProductPriceDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@ToString
@Entity
@Table(name = "tb_productPrice")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String description;

    @Setter
    private BigDecimal value;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductPrice(ProductPriceDTO data, Product product) {
        this.description = data.description();
        this.value = data.value();
        this.product = product;
    }
}
