package com.zurich.insurance_management.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 6062363222694608401L;

    @Schema(description = "Indicates whether the operation was successful", example = "true")
    private boolean result;

    public CommonResponse(boolean result) {
        this.result = result;
    }
}
