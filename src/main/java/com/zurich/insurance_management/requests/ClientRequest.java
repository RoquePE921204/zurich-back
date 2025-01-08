package com.zurich.insurance_management.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zurich.insurance_management.requests.groups.RequiredGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientRequest implements Serializable {

    private static final long serialVersionUID = 2556958350271559608L;

    @NotNull(groups = RequiredGroup.class, message = "El identificador es obligatorio")
    @Pattern(regexp = "\\d{10}", groups = RequiredGroup.class, message = "El identificador debe contener exactamente 10 dígitos")
    @Schema(description = "Unique client identifier (must contain 10 digits)", example = "1234567890")
    private String id;

    @Schema(description = "User identifier associated with the client", example = "123456")
    private Long userId;

    @NotBlank(message = "El nombre completo es requerido")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre no debe contener números ni caracteres especiales")
    @Schema(description = "The client's full name", example = "Roque Roque")
    private String fullName;

    @NotNull(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Schema(description = "The client's email address", example = "roque@example.com")
    private String email;

    @NotBlank(message = "El teléfono es requerido")
    @Schema(description = "The client's contact phone number", example = "1234567890")
    private String telephone;
}
