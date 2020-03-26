package io.mohammad.apiquiz.module.cms.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Accessors(chain = true)
@Entity
public class SaleItem extends Versioned{

    @Id
    @GeneratedValue
    private Long id;


    private Integer quantity;

    private BigDecimal price;
    /**
     * the Sale object which the item belong to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Sale sale;

    /**
     * the Product in the Item
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Product product;


    public String toString() {
        return "SaleItem(id=" + this.getId() +
                ", quantity=" + this.getQuantity() +
                ", price=" + this.getPrice() +
                ", sale.id=" + this.getSale().getId() +
                ", product.id=" + this.getProduct().getId() + ")";
    }
}
