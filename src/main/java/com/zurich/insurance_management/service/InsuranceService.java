package com.zurich.insurance_management.service;

import java.util.List;

import com.zurich.insurance_management.custom.exceptions.GeneralControlledException;
import com.zurich.insurance_management.requests.InsuranceRequest;
import com.zurich.insurance_management.requests.ReadDeleteRequest;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.responses.InsuranceResponse;

public interface InsuranceService {

	public List<InsuranceResponse> getInsuranceList(ReadDeleteRequest request) throws GeneralControlledException;

	public InsuranceResponse getInsurance(String id) throws GeneralControlledException;

	public CommonResponse createInsurance(InsuranceRequest request) throws GeneralControlledException;

	public CommonResponse updateInsurance(InsuranceRequest request, boolean forzeId) throws GeneralControlledException;
	
	public CommonResponse deleteInsurance(String id) throws GeneralControlledException;

}
