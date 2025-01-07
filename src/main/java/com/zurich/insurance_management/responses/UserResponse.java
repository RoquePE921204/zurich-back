package com.zurich.insurance_management.responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse implements Serializable {

	private static final long serialVersionUID = -8591857927563356629L;

	private String role;
	private ClientResponse client;
}
