package io.mohammad.apiquiz.module.cms.services;

import io.mohammad.apiquiz.container.exception.BadInputException;
import io.mohammad.apiquiz.container.exception.CmsError;
import io.mohammad.apiquiz.container.exception.ManagedException;
import io.mohammad.apiquiz.module.cms.dtos.ProductRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.ProductViewDto;
import io.mohammad.apiquiz.module.cms.entities.Product;
import io.mohammad.apiquiz.module.cms.repositories.CategoryRepository;
import io.mohammad.apiquiz.module.cms.repositories.ProductRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * created by Mohammad Nosairat on 3/24/2020.
 */
@RequiredArgsConstructor
@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryservice;

    public Product create(ProductRequestDto productRequestDto) {
        validateCategory(productRequestDto.categoryId);
        Product p = new Product()
                .setName(productRequestDto.name)
                .setCreationDate(Instant.now())
                .setDescription(productRequestDto.description)
                .setCategory(categoryRepository.getOne(productRequestDto.categoryId));

        return productRepository.save(p);
    }

    private void validateCategory(Long categoryId) {
        boolean exist = categoryRepository.existsById(categoryId);
        if (!exist)
            throw new BadInputException().addError("categoryId","not found");
    }

    public Product update(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ManagedException(CmsError.NOT_FOUND, "Product Not Found"));
        if (productRequestDto.categoryId != product.getCategory().getId().longValue())
            validateCategory(productRequestDto.categoryId);

        product.setName(productRequestDto.name)
                .setDescription(productRequestDto.description)
                .setCategory(categoryRepository.getOne(productRequestDto.categoryId));
        return productRepository.save(product);

    }


    public ProductViewDto view(Long id) throws NotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ManagedException(CmsError.NOT_FOUND,"Product Not Found"));
        return  new ProductViewDto(product);

    }

    public List<ProductViewDto> viewAll() {
        List<ProductViewDto> productViewDtos = productRepository.findAll().stream()
                .map(e->new ProductViewDto(e))
                .collect(toList());
        return productViewDtos;
    }
}
