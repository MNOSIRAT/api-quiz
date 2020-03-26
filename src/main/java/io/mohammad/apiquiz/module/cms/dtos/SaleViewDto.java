package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.module.cms.entities.Sale;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SaleViewDto {

    public Long id;
    public Instant creationDate;
    public BigDecimal total;
    public ClientViewDto client;

    public String seller;
    public List<SaleItemViewDto> saleItems = new ArrayList<>();


    public SaleViewDto(Sale sale) {
        this.id = sale.getId();
        this.creationDate = sale.getCreationDate();
        this.total = sale.getItems().stream()
                //.peek(i -> saleItems.add(new SaleItemViewDto(i)))
                .map(i -> i.getPrice().multiply(new BigDecimal(i.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        this.seller = sale.getSeller();

//        this.saleItems = sale.getItems().stream()
//                .map(e->new SaleItemViewDto(e))
//                .collect(Collectors.toList());
//

    }

}
