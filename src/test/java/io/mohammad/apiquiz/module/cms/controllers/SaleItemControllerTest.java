package io.mohammad.apiquiz.module.cms.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mohammad.apiquiz.module.cms.dtos.SaleItemCreateRequestDto;
import io.mohammad.apiquiz.module.cms.dtos.SaleItemEditRequestDto;
import io.mohammad.apiquiz.module.cms.entities.*;
import io.mohammad.apiquiz.module.cms.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestBootsrap
class SaleItemControllerTest {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Test
    void create() throws Exception {
        Category category = new Category();
        category.setName("testCategory");
        category = this.categoryRepository.save(category);

        Client client = new Client();
        client.setName("rami").setLastName("radi").setMobile("099654");
        client = clientRepository.save(client);

        Sale sale = new Sale();
        sale.setSeller("Salah")
                .setCreationDate(Instant.now())
                .setClient(client);
        sale = saleRepository.save(sale);

        Product product2 = new Product();
        product2.setName("lazer")
                .setCreationDate(Instant.now())
                .setDescription("a game is used by children")
                .setCategory(category);
        product2 = productRepository.save(product2);

        SaleItemCreateRequestDto dto = new SaleItemCreateRequestDto();
        {
            dto.quantity = 3;
            dto.saleId=sale.getId();
            dto.price = new BigDecimal(100);
            dto.productId=product2.getId();
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/api/saleitems/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String s = result.getResponse().getHeader("Location");
        Assertions.assertNotNull(s);
        Long recordId = Long.parseLong(s.substring(s.lastIndexOf("/") + 1));
        Optional<SaleItem> createdSaleItem = saleItemRepository.findById( recordId);
        Assertions.assertTrue(createdSaleItem.isPresent());

    }

    @Test
    void edit() throws Exception {
        Category category = new Category();
        category.setName("testCategory");
        category = this.categoryRepository.save(category);

        Client client = new Client();
        client.setName("rami").setLastName("radi").setMobile("099654");
        client = clientRepository.save(client);

        Sale sale = new Sale();
        sale.setSeller("Salah")
                .setCreationDate(Instant.now())
                .setClient(client);
        sale = saleRepository.save(sale);

        Product product2 = new Product();
        product2.setName("lazer")
                .setCreationDate(Instant.now())
                .setDescription("a game is used by children")
                .setCategory(category);
        product2 = productRepository.save(product2);

        SaleItem saleItem = new SaleItem();
        saleItem.setSale(sale)
                .setQuantity(2)
                .setPrice(new BigDecimal(500))
                .setProduct(product2);
        saleItemRepository.save(saleItem);

        SaleItemEditRequestDto dto = new SaleItemEditRequestDto();
        {
            dto.quantity = 3;
            dto.price = new BigDecimal(700);
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/api/saleitems/"+saleItem.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        SaleItem updatedSaleItem = this.saleItemRepository.getOne(saleItem.getId());

        Assertions.assertTrue(updatedSaleItem.getQuantity().equals(3));

    }
}