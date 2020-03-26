package io.mohammad.apiquiz.module.cms.dtos;

import io.mohammad.apiquiz.module.cms.entities.Product;

import java.time.Instant;

public class ProductViewDto {

    public Long id;
    public String name;
    public String description;
    public Instant creationDate;
    public String category;

    public ProductViewDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        category = product.getCategory().getName();
        creationDate = product.getCreationDate();
    }
}
