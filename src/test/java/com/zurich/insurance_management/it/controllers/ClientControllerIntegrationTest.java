package com.zurich.insurance_management.it.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zurich.insurance_management.controllers.ClientController;
import com.zurich.insurance_management.repository.ClientRepository;
import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.requests.ReadDeleteRequest;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.service.ClientService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class ClientControllerIntegrationTest {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientService service;

    @Autowired
    private ClientController controller;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        ClientRequest requeest = new ClientRequest("1234567890", null, "Roque Roque", "ids.roque.pime@zurichexample.com", "1234567890");
        repository.deleteAll();
        service.updateClient(requeest, true);
    }

    @Test
    void testGetClientList() throws Exception {
        mockMvc.perform(get("/client/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));;
    }

    @Test
    void testGetClient() throws Exception {
        String clientId = service.getClientList().get(0).getId();
        String fullName = service.getClientList().get(0).getFullName();
        ReadDeleteRequest request = new ReadDeleteRequest();
        request.setId(clientId);
        mockMvc.perform(get("/client").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.fullName").value(fullName));
    }

    @Test
    void testCreateClient() throws Exception {
        ClientRequest request = new ClientRequest();
        request.setFullName("Roque Roque");
        request.setEmail("ids.roque.roque@zurichexample.com");
        request.setTelephone("1234567890");
        mockMvc.perform(post("/client").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    void testUpdateClient() throws Exception {
        ClientRequest request = new ClientRequest();
        request.setId("1234567890");
        request.setFullName("Roque Roque");
        request.setEmail("roque.roque@zurichexample.com");
        request.setTelephone("9619123802");
        mockMvc.perform(put("/client").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    void testDeleteClient() throws Exception {
        String clientId = service.getClientList().get(0).getId();
        ReadDeleteRequest request = new ReadDeleteRequest();
        request.setId(clientId);
        mockMvc.perform(delete("/client").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
