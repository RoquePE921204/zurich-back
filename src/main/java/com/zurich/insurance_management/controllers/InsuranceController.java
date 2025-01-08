package com.zurich.insurance_management.controllers;

import com.zurich.insurance_management.requests.InsuranceRequest;
import com.zurich.insurance_management.requests.ReadDeleteRequest;
import com.zurich.insurance_management.requests.groups.OptionalGroup;
import com.zurich.insurance_management.requests.groups.RequiredGroup;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.responses.GlobalExceptionResponse;
import com.zurich.insurance_management.responses.InsuranceResponse;
import com.zurich.insurance_management.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "insurance")
@Tag(name = "Insurance", description = "Operations related to insurances")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200"
        , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class InsuranceController {

    @Autowired
    private InsuranceService service;

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get list of insurances", description = "Returns a list of insurances based on a given ID.")
    @ApiResponse(responseCode = "200", description = "List of insurances retrieved successfully")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public List<InsuranceResponse> getInsuranceList(@RequestBody ReadDeleteRequest request) {
        return this.service.getInsuranceList(request);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a specific insurance by ID", description = "Returns the insurance object for the provided ID.")
    @ApiResponse(responseCode = "200", description = "Insurance retrieved successfully")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public InsuranceResponse getInsurance(@RequestBody ReadDeleteRequest request) {
        return this.service.getInsurance(request.getId());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new insurance", description = "Creates a new insurance record.")
    @ApiResponse(responseCode = "200", description = "Insurance created successfully")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public CommonResponse createInsurance(@Validated(OptionalGroup.class) @RequestBody InsuranceRequest request) {
        return this.service.createInsurance(request);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an existing insurance", description = "Updates an existing insurance record.")
    @ApiResponse(responseCode = "200", description = "Insurance updated successfully")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public CommonResponse updateInsurance(@Validated(RequiredGroup.class) @RequestBody InsuranceRequest request) {
        return this.service.updateInsurance(request, false);
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete an existing insurance", description = "Deletes an existing insurance record by ID.")
    @ApiResponse(responseCode = "200", description = "Insurance deleted successfully")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public CommonResponse deleteClient(@RequestBody ReadDeleteRequest request) {
        return this.service.deleteInsurance(request.getId());
    }

}
