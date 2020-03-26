package io.mohammad.apiquiz.module.cms.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mohammad.apiquiz.module.cms.dtos.ProductRequestDto;
import io.mohammad.apiquiz.module.cms.entities.Category;
import io.mohammad.apiquiz.module.cms.entities.Product;
import io.mohammad.apiquiz.module.cms.repositories.CategoryRepository;
import io.mohammad.apiquiz.module.cms.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Mohammad Nosairat 3/25/2020
 */

@TestBootsrap
public class ProductControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createCatigoryNotFound() throws Exception {
        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.name = "magic box";
            dto.description = "is a magic box which sings";
            dto.categoryId = 1L;
        }

        mvc.perform(
                MockMvcRequestBuilders.post("/api/products/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createBadInput() throws Exception {
        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.description = "is a magic box which sings";
            dto.categoryId = 1L;
        }

        mvc.perform(
                MockMvcRequestBuilders.post("/api/products/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void create() throws Exception {
        Category category = CreateCategory();

        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.name = "magic box";
            dto.description = "is a magic box which sings";
            dto.categoryId = category.getId();
        }

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/api/products/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        Long idFromLocation = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));
        Optional<Product> p = productRepository.findById(idFromLocation);
        Assertions.assertTrue(p.isPresent());
    }


    @Test
    public void testEdit() throws Exception {
        Category category = CreateCategory();
        Product product = new Product();
        product.setName("light box")
                .setCreationDate(Instant.now())
                .setDescription("Box to light the house")
                .setCategory(category);
        productRepository.save(product);

        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.name = "Night lighter";
            dto.description = "a lighter is used in forst";
            dto.categoryId = category.getId();
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/api/products/" + product.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Optional<Product> p = productRepository.findById(product.getId());
        Assertions.assertTrue(p.isPresent());
        Assertions.assertTrue(p.get().getName().equals("Night lighter"));
    }

    private Category CreateCategory() {
        Category category = new Category();
        category.setName("testCategory");
        category = this.categoryRepository.save(category);
        return category;
    }

    @Test
    public void testGet() throws Exception {
        Category category = CreateCategory();
        Product product = new Product();
        product.setName("gun")
                .setCreationDate(Instant.now())
                .setDescription("a game is used by children")
                .setCategory(category);

        productRepository.save(product);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Optional<Product> p = productRepository.findById(node.get("id").asLong());
        Assertions.assertTrue(p.isPresent());
        Assertions.assertTrue(p.get().getName().equals("gun"));

    }


    @Test
    public void testGetAll() throws Exception {
        Category category = CreateCategory();

        Product product = new Product();
        product.setName("gun")
                .setCreationDate(Instant.now())
                .setDescription("a game is used by children")
                .setCategory(category);
        productRepository.save(product);

        Product product2 = new Product();
        product2.setName("lazer")
                .setCreationDate(Instant.now())
                .setDescription("a game is used by children")
                .setCategory(category);
        productRepository.save(product2);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());

        Assertions.assertTrue(node.isArray());
        Assertions.assertNull(node.get(3));


    }

    @Test
    public void testGetNotFound() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/products/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

}
