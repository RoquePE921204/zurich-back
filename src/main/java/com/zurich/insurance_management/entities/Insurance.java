package com.zurich.insurance_management.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "insurances")
public class Insurance {

	@Id
	private Long id;
	
	@Column(name = "client_id")
	private Long clientId;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "insured_amount")
	private Double insuredAmount;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "expiration_date")
	private LocalDate expirationDate;
}
