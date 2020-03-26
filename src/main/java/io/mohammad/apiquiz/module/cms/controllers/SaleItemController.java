package io.mohammad.apiquiz.module.cms.controllers;


import io.mohammad.apiquiz.module.cms.dtos.SaleItemCreateRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.SaleItemEditRequestDto;
import io.mohammad.apiquiz.module.cms.entities.SaleItem;
import io.mohammad.apiquiz.module.cms.services.SaleItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/saleitems")
public class SaleItemController {
    private final SaleItemService saleItemService;

    @PostMapping(path = "/")
    public ResponseEntity create(@RequestBody SaleItemCreateRequestDto requestDto) {
        SaleItem c = saleItemService.create(requestDto);
        return ResponseEntity.created(URI.create("/api/clients/" + c.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id,
                               @RequestBody SaleItemEditRequestDto requestDto) {
        SaleItem p = saleItemService.update(id, requestDto);
        return ResponseEntity.ok().build();
    }
}
