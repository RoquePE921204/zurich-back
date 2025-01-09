package com.zurich.insurance_management.service;

import java.util.List;

import com.zurich.insurance_management.custom.exceptions.GeneralControlledException;
import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.responses.ClientResponse;
import com.zurich.insurance_management.responses.CommonResponse;

public interface ClientService {

	public List<ClientResponse> getClientList();

	public ClientResponse getClient(String id) throws GeneralControlledException;

	public CommonResponse createClient(ClientRequest request) throws GeneralControlledException;

	public CommonResponse updateClient(ClientRequest request, boolean forzeId) throws GeneralControlledException;
	
	public CommonResponse deleteClient(String id) throws GeneralControlledException;
}
