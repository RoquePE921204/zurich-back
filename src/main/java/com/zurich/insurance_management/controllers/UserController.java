package com.zurich.insurance_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zurich.insurance_management.requests.LoginRequest;
import com.zurich.insurance_management.responses.UserResponse;
import com.zurich.insurance_management.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserResponse login(@RequestBody LoginRequest request) {
		return this.service.login(request);
	}
}
