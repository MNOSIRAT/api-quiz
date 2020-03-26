package io.mohammad.apiquiz.module.cms.services;

import io.mohammad.apiquiz.container.exception.CmsError;
import io.mohammad.apiquiz.container.exception.ManagedException;
import io.mohammad.apiquiz.module.cms.dtos.SaleRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.SaleViewDto;
import io.mohammad.apiquiz.module.cms.entities.Sale;
import io.mohammad.apiquiz.module.cms.repositories.ClientRepository;
import io.mohammad.apiquiz.module.cms.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;


    public SaleViewDto get(Long id) {
        Sale sale = saleRepository.findByIdFetchItemsAndClient(id)
                .orElseThrow(() -> new ManagedException(CmsError.NOT_FOUND, "Sale Not Found"));
        SaleViewDto dto = new SaleViewDto(sale);
        return dto;
    }

    public Sale create(SaleRequestDto saleRequestDto) {
        validateClinet(saleRequestDto.clientId);

        Sale s = new Sale().setSeller(saleRequestDto.seller).setCreationDate(Instant.now())
                .setClient(clientRepository.getOne(saleRequestDto.clientId));
        s.setCreationDate(Instant.now());

        return saleRepository.save(s);
    }

    private void validateClinet(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ManagedException(CmsError.NOT_FOUND, "Client Not Found");
        }
    }

    public Sale update(Long id, SaleRequestDto saleRequestDto) {

        Sale s = validateIdAndGetSale(id);
        validateClinet(saleRequestDto.clientId);
        s.setSeller(saleRequestDto.seller).setClient(clientRepository.getOne(saleRequestDto.clientId));
        return saleRepository.save(s);
    }

    private Sale validateIdAndGetSale(Long id) {
        Sale s = saleRepository.getOne(id);
        if (s == null) {
            throw new ManagedException(CmsError.NOT_FOUND, "Sale Not Found");
        }
        return s;

    }
}
