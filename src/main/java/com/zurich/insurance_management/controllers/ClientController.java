package com.zurich.insurance_management.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.requests.ReadDeleteRequest;
import com.zurich.insurance_management.requests.groups.OptionalGroup;
import com.zurich.insurance_management.requests.groups.RequiredGroup;
import com.zurich.insurance_management.responses.ClientResponse;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.service.ClientService;

@RestController
@RequestMapping(value = "client")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get list of all clients", description = "Fetches the list of all clients in the system.")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of clients")
	@ApiResponse(responseCode = "500", description = "Unexpected error")
	@ResponseBody
	public List<ClientResponse> getClientList() {
		return this.service.getClientList();
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get a specific client by ID", description = "Fetches a client based on the provided ID.")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved the client")
	@ApiResponse(responseCode = "400", description = "One or more properties fails their validations")
	@ApiResponse(responseCode = "406", description = "Controlled error: See the description")
	@ApiResponse(responseCode = "500", description = "Unexpected error")
	@ResponseBody
	public ClientResponse gettClient(@RequestBody ReadDeleteRequest request) {
		return this.service.getClient(request.getId());
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a new client", description = "Creates a new client with the provided data.")
	@ApiResponse(responseCode = "200", description = "Client successfully created")
	@ApiResponse(responseCode = "400", description = "One or more properties fails their validations")
	@ApiResponse(responseCode = "406", description = "Controlled error: See the description")
	@ApiResponse(responseCode = "500", description = "Unexpected error")
	@ResponseBody
	public CommonResponse createClient(@Validated(OptionalGroup.class) @RequestBody ClientRequest request) {
		return this.service.createClient(request);
	}

	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update an existing client", description = "Updates the details of an existing client.")
	@ApiResponse(responseCode = "200", description = "Client successfully updated")
	@ApiResponse(responseCode = "400", description = "One or more properties fails their validations")
	@ApiResponse(responseCode = "406", description = "Controlled error: See the description")
	@ApiResponse(responseCode = "500", description = "Unexpected error")
	@ResponseBody
	public CommonResponse updateClient(@Validated(RequiredGroup.class) @RequestBody ClientRequest request) {
		return this.service.updateClient(request, false);
	}

	@DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Delete a specific client by ID", description = "Delete a client based on the provided ID.")
	@ApiResponse(responseCode = "200", description = "Client successfully deleted")
	@ApiResponse(responseCode = "400", description = "One or more properties fails their validations")
	@ApiResponse(responseCode = "500", description = "Unexpected error")
	@ResponseBody
	public CommonResponse deletetClient(@RequestBody ReadDeleteRequest request) {
		return this.service.deleteClient(request.getId());
	}
}
