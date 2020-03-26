package io.mohammad.apiquiz.module.cms.controllers;


import io.mohammad.apiquiz.module.cms.dtos.SaleRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.SaleViewDto;
import io.mohammad.apiquiz.module.cms.entities.Sale;
import io.mohammad.apiquiz.module.cms.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    @GetMapping(path = "/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        SaleViewDto saleViewDto = saleService.get(id);
        return ResponseEntity.ok(saleViewDto);
    }

    @PostMapping(path = "/")
    public ResponseEntity create(@RequestBody SaleRequestDto saleRequestDto) {
        Sale c = saleService.create(saleRequestDto);
        return ResponseEntity.created(URI.create("/api/sales/" + c.getId())).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody SaleRequestDto saleRequestDto) {
        Sale p = saleService.update(id, saleRequestDto);
        return ResponseEntity.ok(null);
    }

}
