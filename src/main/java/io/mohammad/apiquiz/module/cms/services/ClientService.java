package io.mohammad.apiquiz.module.cms.services;

import io.mohammad.apiquiz.container.exception.CmsError;
import io.mohammad.apiquiz.container.exception.ManagedException;
import io.mohammad.apiquiz.module.cms.dtos.ClientRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.ClientViewDto;
import io.mohammad.apiquiz.module.cms.entities.Client;
import io.mohammad.apiquiz.module.cms.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mohammad Nosairat on 3/24/2020.
 */
@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientViewDto view(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ManagedException(CmsError.NOT_FOUND, "Client Not Found"));
        return new ClientViewDto(client);
    }

    public Client create(ClientRequestDto clientRequestDto) {
        Client p = new Client()
                .setName(clientRequestDto.name)
                .setLastName(clientRequestDto.lastName)
                .setMobile(clientRequestDto.mobile);
        return clientRepository.save(p);
    }

    public Client update(Long id, ClientRequestDto clientRequestDto) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ManagedException(CmsError.NOT_FOUND, "Client Not Found"));
        client.setName(clientRequestDto.name)
                .setMobile(clientRequestDto.mobile)
                .setLastName(clientRequestDto.lastName);
        return clientRepository.save(client);
    }

    public List<ClientViewDto> viewAll() {
        return clientRepository.findAll().stream()
                .map(e->new ClientViewDto(e))
                .collect(Collectors.toList());
    }
}
