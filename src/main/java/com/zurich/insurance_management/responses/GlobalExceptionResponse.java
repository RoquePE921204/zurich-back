package com.zurich.insurance_management.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalExceptionResponse implements Serializable {

    private static final long serialVersionUID = 6062353232694618301L;

    @Schema(description = """
            A simple error message describing the problem.
            Typically used for:
              - 500 (INTERNAL_SERVER_ERROR)
              - 406 (NOT_ACCEPTABLE)
            If this field has a value, 'messages' is null.
            """
            , example = "Oops, no esperábamos eso, por favor contacte con el administrador.")
    private String message;

    @Schema(description = """
            A map of field errors describing multiple problems.
            Typically used for:
              - 400 (BAD_REQUEST)
            If this map has a value, 'message' is null.
            """
            , example = "{\"email\":\"El email es obligatorio\",\"fullName\":\"El nombre no debe estar vacío\"}")
    private Map<String, String> messages;

    public GlobalExceptionResponse(String message) {
        this.message = message;
    }

    public GlobalExceptionResponse(Map<String, String> messages) {
        this.messages = messages;
    }
}
