package com.zurich.insurance_management.it.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zurich.insurance_management.controllers.UserController;
import com.zurich.insurance_management.entities.User;
import com.zurich.insurance_management.repository.ClientRepository;
import com.zurich.insurance_management.repository.UserRepository;
import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.requests.LoginRequest;
import com.zurich.insurance_management.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class UserControllerIntegrationTest {

    @Autowired
    private UserController controller;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        repository.deleteAll();
        clientRepository.deleteAll();

        User adminUser = new User();
        adminUser.setUsername("adminUser");
        adminUser.setPassword("adminPassword");
        adminUser.setRole("ADMIN");
        repository.save(adminUser);

        User clientUser = new User();
        clientUser.setUsername("clientUser");
        clientUser.setPassword("clientPassword");
        clientUser.setRole("CLIENT");
        repository.save(clientUser);

        ClientRequest clientRequest = new ClientRequest("1234567890", clientUser.getId(), "Santiago Santos", "santi.shago@zurichexample.com", "1234567890");
        clientService.updateClient(clientRequest, true);
    }

    @Test
    void testLoginAsAdmin() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUser("adminUser");
        request.setPassword("adminPassword");

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.client").doesNotExist());
    }

    @Test
    void testLoginAsClient() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUser("clientUser");
        request.setPassword("clientPassword");

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("CLIENT"))
                .andExpect(jsonPath("$.client").isNotEmpty())
                .andExpect(jsonPath("$.client.id").isNotEmpty())
                .andExpect(jsonPath("$.client.fullName").value("Santiago Santos"));
    }
}
