package com.zurich.insurance_management.controllers;

import com.zurich.insurance_management.requests.LoginRequest;
import com.zurich.insurance_management.responses.GlobalExceptionResponse;
import com.zurich.insurance_management.responses.LoginResponse;
import com.zurich.insurance_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
@Tag(name = "User", description = "Operations related to user login")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200", methods = RequestMethod.POST)
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User login", description = "Authenticates the user and returns user information if successful.")
    @ApiResponse(responseCode = "200", description = "User logged in successfully")
    @ApiResponse(responseCode = "400", description = "One or more properties fails their validations"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "406", description = "Controlled error: See the description"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected error"
            , content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @ResponseBody
    public LoginResponse login(@RequestBody LoginRequest request) {
        return this.service.login(request);
    }
}
