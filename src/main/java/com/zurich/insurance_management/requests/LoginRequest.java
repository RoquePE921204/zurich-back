package com.zurich.insurance_management.requests;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = -5919083974799994364L;

	@NotBlank(message = "El usuario es requerido")
	private String user;
	@NotBlank(message = "La contraseña es requerida")
	private String password;

}
