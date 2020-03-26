package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.container.dto.BaseRequestDTO;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
public class ClientRequestDto extends BaseRequestDTO {


    @Column(nullable = false, unique = true)
    public String name;
    @NotBlank
    public String lastName;
    @NotBlank
    public String mobile;
}
