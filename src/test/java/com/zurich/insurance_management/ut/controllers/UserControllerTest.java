package com.zurich.insurance_management.ut.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zurich.insurance_management.controllers.UserController;
import com.zurich.insurance_management.requests.LoginRequest;
import com.zurich.insurance_management.responses.ClientResponse;
import com.zurich.insurance_management.responses.LoginResponse;
import com.zurich.insurance_management.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testLoginAsAdmin() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUser("adminUser");
        request.setPassword("adminPassword");

        LoginResponse response = new LoginResponse();
        response.setRole("ADMIN");

        when(userService.login(request)).thenReturn(response);

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.client").doesNotExist());

        verify(userService, times(1)).login(request);
    }

    @Test
    void testLoginAsClient() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUser("clientUser");
        request.setPassword("clientPassword");

        LoginResponse response = new LoginResponse();
        response.setRole("CLIENT");
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId("1234567890");
        clientResponse.setFullName("Santiago Santos");
        response.setClient(clientResponse);

        when(userService.login(request)).thenReturn(response);

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("CLIENT"))
                .andExpect(jsonPath("$.client").isNotEmpty())
                .andExpect(jsonPath("$.client.id").value("1234567890"))
                .andExpect(jsonPath("$.client.fullName").value("Santiago Santos"));

        verify(userService, times(1)).login(request);
    }
}
