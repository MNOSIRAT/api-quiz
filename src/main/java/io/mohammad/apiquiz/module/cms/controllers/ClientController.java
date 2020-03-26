package io.mohammad.apiquiz.module.cms.controllers;

import io.mohammad.apiquiz.module.cms.dtos.ClientRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.ClientViewDto;
import io.mohammad.apiquiz.module.cms.entities.Client;
import io.mohammad.apiquiz.module.cms.services.ClientService;
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
@RequestMapping(path = "/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        ClientViewDto dto = clientService.view(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> viewAll() {
        List<ClientViewDto> dtos = clientService.viewAll();
        return ResponseEntity.ok(dtos);
    }


    @PostMapping(path = "/")
    public ResponseEntity<?> create(@RequestBody ClientRequestDto clientRequestDto) {
        Client c = clientService.create(clientRequestDto);
        return ResponseEntity.created(URI.create("/api/clients/" + c.getId())).body(null);
    }


    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto) {
        Client p = clientService.update(id, clientRequestDto);
        return ResponseEntity.ok().build();
    }


}
