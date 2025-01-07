package com.zurich.insurance_management.custom.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.zurich.insurance_management.entities.Client;
import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.responses.ClientResponse;

public class ClientMapper {

	public static Client requestToEntity(ClientRequest request) {
		if (request == null) {
			return null;
		}

		Client client = new Client();
		client.setId(request.getId() != null ? Long.valueOf(request.getId()) : null);
		client.setFullName(request.getFullName());
		client.setEmail(request.getEmail());
		client.setTelephone(request.getTelephone());
		client.setUserId(request.getUserId());
		return client;
	}

	public static ClientResponse entityToResponse(Client client) {
		if (client == null) {
			return null;
		}

		ClientResponse response = new ClientResponse();
		response.setId(client.getId() != null ? client.getId().toString() : null);
		response.setUserId(client.getUserId());
		response.setFullName(client.getFullName());
		response.setEmail(client.getEmail());
		response.setTelephone(client.getTelephone());
		return response;
	}

	public static List<ClientResponse> entitiesToResponses(List<Client> clients) {
		if (clients == null) {
			return null;
		}

		return clients.stream().map(ClientMapper::entityToResponse).collect(Collectors.toList());
	}

	public static Long stringToLong(String id) {
		return id != null ? Long.valueOf(id) : null;
	}

	public static String longToString(Long id) {
		return id != null ? id.toString() : null;
	}
}
