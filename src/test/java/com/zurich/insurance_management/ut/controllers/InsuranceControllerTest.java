package com.zurich.insurance_management.ut.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zurich.insurance_management.controllers.InsuranceController;
import com.zurich.insurance_management.requests.InsuranceRequest;
import com.zurich.insurance_management.responses.ClientResponse;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.responses.InsuranceResponse;
import com.zurich.insurance_management.service.ClientService;
import com.zurich.insurance_management.service.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InsuranceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InsuranceService insuranceService;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private InsuranceController insuranceController;

    private ObjectMapper objectMapper;

    private String clientId;
    private String insuranceId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(insuranceController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        clientId = "1234567890";
        insuranceId = "1234567891";

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(clientId);
        clientResponse.setFullName("Roque Roque");
        clientResponse.setEmail("ids.roque.pime@zurichexample.com");
        clientResponse.setTelephone("1234567890");

        InsuranceResponse insuranceResponse = new InsuranceResponse();
        insuranceResponse.setId(insuranceId);
        insuranceResponse.setClientId(clientId);
        insuranceResponse.setType("Vida");
        insuranceResponse.setInsuredAmount(10203.00);
        insuranceResponse.setStartDate(LocalDate.now());
        insuranceResponse.setExpirationDate(LocalDate.now().plusYears(1));

        when(clientService.getClientList()).thenReturn(List.of(clientResponse));
        when(insuranceService.getInsuranceList(clientId)).thenReturn(List.of(insuranceResponse));
    }

    @Test
    void testGetInsuranceList() throws Exception {
        mockMvc.perform(get("/insurance/list/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(insuranceService, times(1)).getInsuranceList(clientId);
    }

    @Test
    void testGetInsurance() throws Exception {
        InsuranceResponse insuranceResponse = new InsuranceResponse();
        insuranceResponse.setId(insuranceId);
        insuranceResponse.setClientId(clientId);
        insuranceResponse.setType("Vida");
        insuranceResponse.setInsuredAmount(10203.00);
        insuranceResponse.setStartDate(LocalDate.now());
        insuranceResponse.setExpirationDate(LocalDate.now().plusYears(1));

        when(insuranceService.getInsurance(insuranceId)).thenReturn(insuranceResponse);

        mockMvc.perform(get("/insurance/" + insuranceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(insuranceId))
                .andExpect(jsonPath("$.clientId").value(clientId))
                .andExpect(jsonPath("$.type").value("Vida"))
                .andExpect(jsonPath("$.insuredAmount").value(10203.00));

        verify(insuranceService, times(1)).getInsurance(insuranceId);
    }

    @Test
    void testCreateInsurance() throws Exception {
        InsuranceRequest request = new InsuranceRequest();
        request.setId(insuranceId);
        request.setClientId(clientId);
        request.setType("Vida");
        request.setInsuredAmount(10000.00);
        request.setStartDate(LocalDate.now());
        request.setExpirationDate(LocalDate.now().plusYears(1));

        CommonResponse response = new CommonResponse(true);
        when(insuranceService.createInsurance(request)).thenReturn(response);

        mockMvc.perform(post("/insurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));

        verify(insuranceService, times(1)).createInsurance(request);
    }

    @Test
    void testUpdateInsurance() throws Exception {
        InsuranceRequest request = new InsuranceRequest();
        request.setId(insuranceId);
        request.setClientId(clientId);
        request.setType("Hogar");
        request.setInsuredAmount(10400.00);
        request.setStartDate(LocalDate.now());
        request.setExpirationDate(LocalDate.now().plusYears(2));

        CommonResponse response = new CommonResponse(true);
        when(insuranceService.updateInsurance(request, false)).thenReturn(response);

        mockMvc.perform(put("/insurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));

        verify(insuranceService, times(1)).updateInsurance(request, false);
    }

    @Test
    void testDeleteInsurance() throws Exception {
        CommonResponse response = new CommonResponse(true);
        when(insuranceService.deleteInsurance(insuranceId)).thenReturn(response);

        mockMvc.perform(delete("/insurance/" + insuranceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));

        verify(insuranceService, times(1)).deleteInsurance(insuranceId);
    }
}
