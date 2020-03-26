package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.container.dto.BaseRequestDTO;
import io.mohammad.apiquiz.module.cms.entities.Client;
import io.mohammad.apiquiz.module.cms.entities.SaleItem;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;
import java.util.Set;

public class SaleRequestDto extends BaseRequestDTO {

    @NotNull
    public Long clientId;

    @NotBlank
    public String seller;
}
