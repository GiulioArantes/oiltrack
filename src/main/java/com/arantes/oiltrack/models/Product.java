package com.arantes.oiltrack.models;


import com.arantes.oiltrack.dto.product.ProductRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tb_products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "name" })
@EqualsAndHashCode(of = { "id" })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Size(max = 100)
    @NotBlank(message = "The attribute is required")
    private String name;

    @Setter
    @Size(max = 1000)
    private String description;

    public Product(ProductRequestDTO data) {
        this.name = data.name();
        this.description = data.description();
    }
}
