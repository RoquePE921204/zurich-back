package com.zurich.insurance_management.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReadDeleteRequest implements Serializable {

	private static final long serialVersionUID = -7275506745578539581L;

	@NotNull(message = "El identificador es obligatorio")
	@Pattern(regexp = "\\d{10}", message = "El identificador debe contener exactamente 10 dígitos")
	private String id;

}
