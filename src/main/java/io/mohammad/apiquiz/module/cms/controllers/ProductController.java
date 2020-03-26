package io.mohammad.apiquiz.module.cms.controllers;


import io.mohammad.apiquiz.module.cms.dtos.ProductRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.ProductViewDto;
import io.mohammad.apiquiz.module.cms.entities.Product;
import io.mohammad.apiquiz.module.cms.services.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) throws NotFoundException {
        ProductViewDto dto = productService.view(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> viewAll() {
        List<ProductViewDto> dtos = productService.viewAll();
        return ResponseEntity.ok(dtos);
    }


    @PostMapping(path = "/")
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productRequestDto) {
        Product p = productService.create(productRequestDto);
        return ResponseEntity.created(URI.create("/api/products/" + p.getId())).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        Product p = productService.update(id, productRequestDto);
        return ResponseEntity.ok().build();
    }


}
