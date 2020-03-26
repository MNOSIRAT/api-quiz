package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.module.cms.entities.Sale;
import io.mohammad.apiquiz.module.cms.entities.SaleItem;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class SaleItemViewDto {
    public Long id;
    public Integer quantity;
    public BigDecimal price;
    public SaleViewDto saleViewDto;

    public SaleItemViewDto(SaleItem saleItem) {
        this.id=saleItem.getId();
        this.quantity=saleItem.getQuantity();
        this.price=saleItem.getPrice();
        this.saleViewDto= new SaleViewDto(saleItem.getSale());
    }
}
