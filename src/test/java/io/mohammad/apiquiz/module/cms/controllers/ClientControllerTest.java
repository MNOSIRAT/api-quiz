package io.mohammad.apiquiz.module.cms.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mohammad.apiquiz.ApiQuizApplication;
import io.mohammad.apiquiz.module.cms.dtos.ClientRequestDto;
import io.mohammad.apiquiz.module.cms.entities.Client;
import io.mohammad.apiquiz.module.cms.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiQuizApplication.class)
@Transactional
class ClientControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;


    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void viewNotFound() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/clients/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void view() throws Exception {
        Client client = new Client();
        client.setName("rami").setLastName("radi").setMobile("099654");
        client = clientRepository.save(client);
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/clients/" + client.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue("rami".equals(node.get("name").asText()));
    }


    @Test
    void viewAll() throws Exception {
        Client client = new Client();
        client.setName("rami").setLastName("radi").setMobile("099654");
        client = clientRepository.save(client);
        Client client2 = new Client();
        client2.setName("hazar").setLastName("recardo").setMobile("099654");
        client2 = clientRepository.save(client2);


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue(node.isArray());

    }


    @Test
    void create() throws Exception {
        ClientRequestDto dto = new ClientRequestDto();
        {
            dto.name = "mikle";
            dto.mobile = "0993459";
            dto.lastName = "amean";
        }


        MvcResult result= mvc.perform(
                MockMvcRequestBuilders.post("/api/clients/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String location= result.getResponse().getHeader("Location");
        Long idFromLocation = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));
        Optional<Client> createdClient = clientRepository.findById(idFromLocation);
        Assertions.assertTrue(createdClient.isPresent());
        if(createdClient.isPresent()) {
            Assertions.assertTrue(createdClient.get().getName().equals("mikle"));
        }
    }

    @Test
    void edit() throws Exception {
        Client client = new Client();
        client.setName("rami").setLastName("radi").setMobile("099654");
        client = clientRepository.save(client);

        ClientRequestDto dto = new ClientRequestDto();
        {
            dto.name = "mikle";
            dto.mobile = "0993459";
            dto.lastName = "amean";
        }


        MvcResult result= mvc.perform(
                MockMvcRequestBuilders.put("/api/clients/"+client.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();



        Optional<Client> updatedClient = clientRepository.findById(client.getId());
        Assertions.assertTrue(updatedClient.isPresent());
        if(updatedClient.isPresent()) {
            Assertions.assertTrue(updatedClient.get().getName().equals("mikle"));
        }
    }
}