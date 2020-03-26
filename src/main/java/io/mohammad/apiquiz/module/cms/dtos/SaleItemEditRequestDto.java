package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.container.dto.BaseRequestDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class SaleItemEditRequestDto extends BaseRequestDTO {

    @NotNull
    @Positive
    public Integer quantity;
    @Positive
    @NotNull
    public BigDecimal price;

}
