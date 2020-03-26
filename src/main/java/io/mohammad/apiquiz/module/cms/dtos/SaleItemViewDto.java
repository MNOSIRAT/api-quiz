package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.module.cms.entities.SaleItem;

import java.math.BigDecimal;

public class SaleItemViewDto {
    public Long id;
    public Integer quantity;
    public BigDecimal price;
    public ProductViewDto product;
    public SaleItemViewDto(SaleItem saleItem) {
        this.id=saleItem.getId();
        this.quantity=saleItem.getQuantity();
        this.price=saleItem.getPrice();
        this.product=new ProductViewDto(saleItem.getProduct());
    }
}
