package com.zurich.insurance_management.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zurich.insurance_management.requests.groups.RequiredGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientRequest implements Serializable {

	private static final long serialVersionUID = 2556958350271559608L;

	@NotNull(groups = RequiredGroup.class, message = "El identificador es obligatorio")
	@Pattern(regexp = "\\d{10}", groups = RequiredGroup.class, message = "El identificador debe contener exactamente 10 dígitos")
	private String id;

	private Long userId;

	@NotBlank(message = "El nombre completo es requerido")
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre no debe contener números ni caracteres especiales")
	private String fullName;

	@NotNull(message = "El email es obligatorio")
	@Email(message = "El email debe ser válido")
	private String email;

	@NotBlank(message = "El teléfono es requerido")
	private String telephone;

}
