package com.zurich.insurance_management.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse implements Serializable {

    private static final long serialVersionUID = 8917141150805551084L;

    @Schema(description = "El identificador único del cliente", example = "1234567890")
    private String id;

    @Schema(description = "El identificador del usuario asociado al cliente", example = "123456")
    private Long userId;

    @Schema(description = "El nombre completo del cliente", example = "Roque Roque")
    private String fullName;

    @Schema(description = "El correo electrónico del cliente", example = "roque@example.com")
    private String email;

    @Schema(description = "El teléfono de contacto del cliente", example = "1234567890")
    private String telephone;

}
