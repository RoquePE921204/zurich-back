package com.zurich.insurance_management.custom.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.zurich.insurance_management.entities.Insurance;
import com.zurich.insurance_management.requests.InsuranceRequest;
import com.zurich.insurance_management.responses.InsuranceResponse;

public class InsuranceMapper {

	public static Insurance requestToEntity(InsuranceRequest request) {
		if (request == null) {
			return null;
		}

		Insurance insurance = new Insurance();
		insurance.setId(stringToLong(request.getId()));
		insurance.setClientId(stringToLong(request.getClientId()));
		insurance.setType(request.getType());
		insurance.setInsuredAmount(request.getInsuredAmount());
		insurance.setStartDate(request.getStartDate());
		insurance.setExpirationDate(request.getExpirationDate());

		return insurance;
	}

	public static InsuranceResponse entityToResponse(Insurance insurance) {
		if (insurance == null) {
			return null;
		}

		InsuranceResponse response = new InsuranceResponse();
		response.setId(longToString(insurance.getId()));
		response.setClientId(longToString(insurance.getClientId()));
		response.setType(insurance.getType());
		response.setInsuredAmount(insurance.getInsuredAmount());
		response.setStartDate(insurance.getStartDate());
		response.setExpirationDate(insurance.getExpirationDate());

		return response;
	}

	public static List<InsuranceResponse> entitiesToResponses(List<Insurance> insurances) {
		if (insurances == null) {
			return null;
		}

		return insurances.stream().map(InsuranceMapper::entityToResponse).collect(Collectors.toList());
	}

	private static Long stringToLong(String id) {
		return id != null ? Long.valueOf(id) : null;
	}

	private static String longToString(Long id) {
		return id != null ? id.toString() : null;
	}
}