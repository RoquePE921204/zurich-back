package com.zurich.insurance_management.responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse implements Serializable {

	private static final long serialVersionUID = 8917141150805551084L;

	private String id;
	private Long userId;
	private String fullName;
	private String email;
	private String telephone;

}
