package com.zurich.insurance_management.ut.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zurich.insurance_management.controllers.ClientController;
import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.requests.ReadDeleteRequest;
import com.zurich.insurance_management.responses.ClientResponse;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class ClientControllerTest {

    @Mock
    private ClientService service;

    @InjectMocks
    private ClientController controller;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetClientList() throws Exception {
        ClientResponse response = new ClientResponse();
        response.setId("1234567890");
        response.setFullName("Roque Roque");
        when(service.getClientList()).thenReturn(Arrays.asList(response));

        mockMvc.perform(get("/client/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1234567890"))
                .andExpect(jsonPath("$[0].fullName").value("Roque Roque"));
    }

    @Test
    void testGetClient() throws Exception {
        ReadDeleteRequest request = new ReadDeleteRequest();
        request.setId("1234567890");
        ClientResponse response = new ClientResponse();
        response.setId("1234567890");
        response.setFullName("Roque Roque");
        when(service.getClient("1234567890")).thenReturn(response);

        mockMvc.perform(get("/client").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1234567890"))
                .andExpect(jsonPath("$.fullName").value("Roque Roque"));
    }

    @Test
    void testCreateClient() throws Exception {
        ClientRequest request = new ClientRequest();
        request.setFullName("Roque Roque");
        request.setEmail("ids.roque@zurichexample.com");
        request.setTelephone("1234567890");
        CommonResponse response = new CommonResponse(true);
        when(service.createClient(request)).thenReturn(response);

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
        request.setEmail("ids.roque@zurichexample.com");
        request.setTelephone("1234567890");
        CommonResponse response = new CommonResponse(true);
        when(service.updateClient(request, false)).thenReturn(response);

        mockMvc.perform(put("/client").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
