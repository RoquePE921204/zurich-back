package com.zurich.insurance_management.it.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zurich.insurance_management.controllers.InsuranceController;
import com.zurich.insurance_management.repository.ClientRepository;
import com.zurich.insurance_management.repository.InsuranceRepository;
import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.requests.InsuranceRequest;
import com.zurich.insurance_management.service.ClientService;
import com.zurich.insurance_management.service.InsuranceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class InsuranceControllerIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clienteService;

    @Autowired
    private InsuranceRepository repository;

    @Autowired
    private InsuranceService service;

    @Autowired
    private InsuranceController controller;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String clientId;
    private String insuranceId;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        ClientRequest clientRequeest = new ClientRequest("1234567890", null, "Roque Roque", "ids.roque.pime@zurichexample.com", "1234567890");
        clientRepository.deleteAll();
        clienteService.updateClient(clientRequeest, true);
        this.clientId = clienteService.getClientList().get(0).getId();

        InsuranceRequest request = new InsuranceRequest("1234567891", clientId, "Vida", 10203.00, LocalDate.now(), LocalDate.now().plusYears(1));
        repository.deleteAll();
        service.updateInsurance(request, true);
        this.insuranceId = service.getInsuranceList(this.clientId).get(0).getId();
    }

    @Test
    void testGetInsuranceList() throws Exception {
        mockMvc.perform(get("/insurance/list/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
        ;
    }

    @Test
    void testGetInsurance() throws Exception {
        mockMvc.perform(get("/insurance/" + insuranceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.clientId").isNotEmpty())
                .andExpect(jsonPath("$.type").isNotEmpty())
                .andExpect(jsonPath("$.insuredAmount").isNotEmpty())
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.expirationDate").isNotEmpty());
    }

    @Test
    void testCreateInsurance() throws Exception {
        InsuranceRequest request = new InsuranceRequest();
        request.setClientId(clientId);
        request.setType("Vida");
        request.setInsuredAmount(10000.00);
        request.setStartDate(LocalDate.now());
        request.setExpirationDate(LocalDate.now().plusYears(1));

        mockMvc.perform(post("/insurance").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    void testUpdateInsurance() throws Exception {
        InsuranceRequest request = new InsuranceRequest();
        request.setId("1234567891");
        request.setClientId(clientId);
        request.setType("Hogar");
        request.setInsuredAmount(10400.00);
        request.setStartDate(LocalDate.now());
        request.setExpirationDate(LocalDate.now().plusYears(2));

        mockMvc.perform(put("/insurance").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    void testDeleteClient() throws Exception {
        mockMvc.perform(delete("/insurance/" + insuranceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
