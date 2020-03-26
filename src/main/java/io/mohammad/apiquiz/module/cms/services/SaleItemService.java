package io.mohammad.apiquiz.module.cms.services;

import io.mohammad.apiquiz.container.exception.BadInputException;
import io.mohammad.apiquiz.container.exception.CmsError;
import io.mohammad.apiquiz.container.exception.ManagedException;
import io.mohammad.apiquiz.module.cms.dtos.SaleItemCreateRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.SaleItemEditRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.SaleItemViewDto;
import io.mohammad.apiquiz.module.cms.entities.Sale;
import io.mohammad.apiquiz.module.cms.entities.SaleItem;
import io.mohammad.apiquiz.module.cms.repositories.ProductRepository;
import io.mohammad.apiquiz.module.cms.repositories.SaleItemRepository;
import io.mohammad.apiquiz.module.cms.repositories.SaleRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;


    @Transactional
    public SaleItem create(SaleItemCreateRequestDto requestDto) {
        validateSale(requestDto.saleId);
        validateProduct(requestDto.productId);

        SaleItem saleItem = new SaleItem();
        Sale sale=saleRepository.getOne(requestDto.saleId);
        saleItem.setPrice(requestDto.price)
                .setQuantity(requestDto.quantity)
                .setSale(sale).setProduct(productRepository.getOne(requestDto.productId));

        saleItem= this.saleItemRepository.save(saleItem);
        log.info("sale Item created: {}", saleItem);
        return saleItem;
    }

    private void validateProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new BadInputException().addError("product","not found");
        }
    }


    private void validateSale(Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new BadInputException().addError("saleId","not found");
        }
    }

    public SaleItemViewDto get(Long id) throws NotFoundException {
        SaleItem saleItem = this.saleItemRepository.findById(id)
                .orElseThrow(()->new ManagedException(CmsError.NOT_FOUND,"SaleItem not found"));
        return new SaleItemViewDto(saleItem);
    }

    @Transactional
    public SaleItem update(Long id, SaleItemEditRequestDto requestDto) {
        SaleItem saleItem = saleItemRepository.findById(id)
                .orElseThrow(() -> new ManagedException(CmsError.NOT_FOUND, "Sale Item Not Found"));

        log.info("sale Item before update: {}", saleItem);

        saleItem.setPrice(requestDto.price)
                .setQuantity(requestDto.quantity);

        log.info("sale Item after update: {}", saleItem);
        return this.saleItemRepository.save(saleItem);
    }

}
