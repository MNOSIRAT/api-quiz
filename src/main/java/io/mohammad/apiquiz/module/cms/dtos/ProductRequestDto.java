package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.container.dto.BaseRequestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
public class ProductRequestDto extends BaseRequestDTO {

    @NotBlank
    public String name;
    public String description;

    @NotNull
    public Long categoryId;
}
