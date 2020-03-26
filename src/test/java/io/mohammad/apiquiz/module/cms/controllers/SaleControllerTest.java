package io.mohammad.apiquiz.module.cms.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mohammad.apiquiz.module.cms.dtos.SaleRequestDto;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestBootsrap
class SaleControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;



    @Test
    void get() throws Exception {

        Client client = new Client();
        client.setName("rami").setLastName("radi").setMobile("099654");
        client = clientRepository.save(client);

        Sale sale = new Sale();
        sale.setSeller("Salah").setCreationDate(Instant.now()).setClient(client);
        sale = saleRepository.save(sale);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/sales/" + sale.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue("Salah".equals(node.get("seller").asText()));

    }


    @Test
    void getWithSaleItem() throws Exception {
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
        sale.getItems().add(saleItem);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/sales/" + sale.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue("Salah".equals(node.get("seller").asText()));
        Assertions.assertTrue(1000 == node.get("total").asInt());

    }


    @Test
    void create() throws Exception {
        Client c = new Client();
        c.setName("ali").setLastName("sameh").setMobile("0999");
        c = clientRepository.save(c);

        SaleRequestDto dto = new SaleRequestDto();
        {
            dto.seller = "RADI";
            dto.clientId = c.getId();
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/api/sales/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String s = result.getResponse().getHeader("Location");
        Assertions.assertNotNull(s);
        Long recordId = Long.parseLong(s.substring(s.lastIndexOf("/") + 1));
        Sale p = em.find(Sale.class, recordId);
        Assertions.assertNotNull(p);

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

        SaleRequestDto dto = new SaleRequestDto();
        {
            dto.seller = "sami";
            dto.clientId = client.getId();
        }



        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/api/sales/"+sale.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        Sale updatedSale = this.saleRepository.getOne(sale.getId());

        Assertions.assertTrue(updatedSale.getSeller().equals("sami"));

    }
}