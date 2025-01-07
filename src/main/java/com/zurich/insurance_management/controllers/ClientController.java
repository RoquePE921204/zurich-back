package com.zurich.insurance_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@ResponseBody
	public List<ClientResponse> getClientList() {
		return this.service.getClientList();
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ClientResponse gettClient(@RequestBody ReadDeleteRequest request) {
		return this.service.getClient(request.getId());
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CommonResponse createClient(@Validated(OptionalGroup.class) @RequestBody ClientRequest request) {
		return this.service.createClient(request);
	}

	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CommonResponse updateClient(@Validated(RequiredGroup.class) @RequestBody ClientRequest request) {
		return this.service.updateClient(request, false);
	}
}
