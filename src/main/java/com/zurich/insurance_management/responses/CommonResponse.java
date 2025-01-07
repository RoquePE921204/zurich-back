package com.zurich.insurance_management.responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse implements Serializable {

	private static final long serialVersionUID = 6062363222694608401L;

	private boolean result;

	public CommonResponse(boolean result) {
		this.result = result;
	}
}
