package com.zurich.insurance_management.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = -8591857927563356629L;

    @Schema(description = "Role assigned to the user (e.g., ADMIN, CLIENT)", example = "CLIENT")
    private String role;

    @Schema(description = "Client information associated with the user")
    private ClientResponse client;
}
