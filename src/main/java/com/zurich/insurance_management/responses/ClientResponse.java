package com.zurich.insurance_management.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse implements Serializable {

    private static final long serialVersionUID = 8917141150805551084L;

    @Schema(description = "Unique client identifier", example = "1234567890")
    private String id;

    @Schema(description = "User identifier associated with the client", example = "123456")
    private Long userId;

    @Schema(description = "The client's full name", example = "Roque Roque")
    private String fullName;

    @Schema(description = "The client's email address", example = "roque@example.com")
    private String email;

    @Schema(description = "The client's contact phone number", example = "1234567890")
    private String telephone;
}
