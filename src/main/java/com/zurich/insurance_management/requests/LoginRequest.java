package com.zurich.insurance_management.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -5919083974799994364L;

    @NotBlank(message = "El usuario es requerido")
    @Schema(description = "User's login identifier", example = "adminuser")
    private String user;

    @NotBlank(message = "La contraseña es requerida")
    @Schema(description = "User's password", example = "admin123")
    private String password;
}
