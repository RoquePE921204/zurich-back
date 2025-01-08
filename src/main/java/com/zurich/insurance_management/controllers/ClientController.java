package com.zurich.insurance_management.controllers;

import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.requests.groups.OptionalGroup;
import com.zurich.insurance_management.requests.groups.RequiredGroup;
import com.zurich.insurance_management.responses.ClientResponse;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.responses.GlobalExceptionResponse;
import com.zurich.insurance_management.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "client")
@Tag(name = "Client", description = "Operations related to clients")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200"
        , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get list of all clients", description = "Fetches the list of all clients in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of clients")
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public List<ClientResponse> getClientList() {
        return this.service.getClientList();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a specific client by ID", description = "Fetches a client based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the client")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public ClientResponse getClient(@PathVariable @Pattern(regexp = "\\d{10}"
            , message = "El identificador debe contener exactamente 10 dígitos") String id) {
        return this.service.getClient(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new client", description = "Creates a new client with the provided data.")
    @ApiResponse(responseCode = "200", description = "Client successfully created")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public CommonResponse createClient(@Validated(OptionalGroup.class) @RequestBody ClientRequest request) {
        return this.service.createClient(request);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an existing client", description = "Updates the details of an existing client.")
    @ApiResponse(responseCode = "200", description = "Client successfully updated")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public CommonResponse updateClient(@Validated(RequiredGroup.class) @RequestBody ClientRequest request) {
        return this.service.updateClient(request, false);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete a specific client by ID", description = "Delete a client based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Client successfully deleted")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public CommonResponse deletetClient(@PathVariable @Pattern(regexp = "\\d{10}"
            , message = "El identificador debe contener exactamente 10 dígitos") String id) {
        return this.service.deleteClient(id);
    }
}
