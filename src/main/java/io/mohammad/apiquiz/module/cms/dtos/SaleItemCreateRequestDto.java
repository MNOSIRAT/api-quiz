package io.mohammad.apiquiz.module.cms.dtos;

import javax.validation.constraints.NotNull;

public class SaleItemCreateRequestDto extends SaleItemEditRequestDto {
    @NotNull
    public Long saleId;

    @NotNull
    public Long productId;


}
