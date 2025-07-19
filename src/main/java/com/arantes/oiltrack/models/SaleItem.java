package com.arantes.oiltrack.models;

import com.arantes.oiltrack.dto.saleItem.SaleItemRequestDTO;
import com.arantes.oiltrack.models.pk.SaleItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
@Entity
@Table(name = "tb_sale_item")
public class SaleItem {

    @EmbeddedId
    private SaleItemPK id = new SaleItemPK();

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private Double price;

    public SaleItem(Sale sale, Product product, Integer quantity, Double price) {
        id.setSale(sale);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Sale getSale() {
        return id.getSale();
    }

    public void setSale(Sale sale) {
        id.setSale(sale);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Double getSubTotal() {
        return price * quantity;
    }

    public SaleItem(Product product, SaleItemRequestDTO data) {
        id.setProduct(product);
        this.quantity = data.quantity();
        this.price = data.price();
    }
}
