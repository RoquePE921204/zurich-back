package com.zurich.insurance_management.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zurich.insurance_management.custom.validate.validations.ValidExpirationDate;
import com.zurich.insurance_management.requests.groups.RequiredGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceRequest implements Serializable {

    private static final long serialVersionUID = -3529677921542092034L;

    @NotNull(groups = RequiredGroup.class, message = "El identificador es obligatorio")
    @Pattern(regexp = "\\d{10}", groups = RequiredGroup.class, message = "El identificador debe contener exactamente 10 dígitos")
    @Schema(description = "Unique insurance identifier (must contain 10 digits)", example = "1234567890")
    private String id;

    @NotNull(message = "El cliente es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "El cliente debe contener exactamente 10 dígitos")
    @Schema(description = "Unique client identifier associated with the insurance (must contain 10 digits)", example = "0987654321")
    private String clientId;

    @NotBlank(message = "El tipo es obligatorio")
    @Pattern(regexp = "^(Vida|Automóvil|Salud|Hogar)$", message = "El tipo debe ser uno de los siguientes: Vida, Automóvil, Salud, Hogar")
    @Schema(description = "Type of insurance: must be one of Vida, Automóvil, Salud, or Hogar", example = "Vida")
    private String type;

    @NotNull(message = "El monto asegurado es obligatorio")
    @Positive(message = "El monto asegurado debe ser un valor numérico positivo")
    @Schema(description = "Positive numeric value representing the insured amount", example = "50000")
    private Double insuredAmount;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "La fecha de inicio debe ser una fecha presente  o futura")
    @Schema(description = "Start date of insurance coverage (must be present or future)", example = "2025-01-01")
    private LocalDate startDate;

    @NotNull(message = "La fecha de expiración es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "La fecha de expiración debe ser una fecha futura")
    @ValidExpirationDate
    @Schema(description = "Expiration date of insurance coverage (must be a future date)", example = "2026-01-01")
    private LocalDate expirationDate;
}
