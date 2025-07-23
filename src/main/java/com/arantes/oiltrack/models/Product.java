package com.arantes.oiltrack.models;


import com.arantes.oiltrack.dto.product.ProductRequestDTO;
import com.arantes.oiltrack.dto.productPrice.ProductPriceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "name" })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Size(max = 100)
    @NotBlank(message = "The attribute is required")
    private String name;

    @Setter
    @ManyToOne
    @JsonIgnoreProperties("products")
    private Category category;

    @Setter
    @Size(max = 1000)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPrice> prices = new ArrayList<>();

    @OneToMany(mappedBy = "id.product")
    private Set<SaleItem> items = new HashSet<>();

    @JsonIgnore
    public Set<Sale> getSales() {
        Set<Sale> set = new HashSet<>();
        for(SaleItem item : items) {
            set.add(item.getSale());
        }
        return set;
    }

    public Product(ProductRequestDTO data, Category category) {
        this.name = data.name();
        this.category = category;
        this.description = data.description();
        this.prices = data.prices().stream()
                .map(dto -> new ProductPrice(dto, this))
                .toList();
    }
}
