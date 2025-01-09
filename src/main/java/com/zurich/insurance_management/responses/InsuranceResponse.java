package com.zurich.insurance_management.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceResponse implements Serializable {

    private static final long serialVersionUID = -8751219181106749564L;

    @Schema(description = "Unique insurance identifier", example = "1234567890")
    private String id;

    @Schema(description = "Identifier of the client associated with the insurance", example = "0987654321")
    private String clientId;

    @Schema(description = "Type of insurance (e.g., Vida, Automóvil, Salud, Hogar)", example = "Vida")
    private String type;

    @Schema(description = "Insured amount", example = "50000")
    private Double insuredAmount;

    @Schema(description = "Start date of the insurance coverage", example = "2025-01-01")
    private LocalDate startDate;

    @Schema(description = "Expiration date of the insurance coverage", example = "2026-01-01")
    private LocalDate expirationDate;
}
