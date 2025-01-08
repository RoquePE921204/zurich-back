package com.zurich.insurance_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zurich.insurance_management.custom.exceptions.GeneralControlledException;
import com.zurich.insurance_management.custom.mappers.ClientMapper;
import com.zurich.insurance_management.entities.Client;
import com.zurich.insurance_management.repository.ClientRepository;
import com.zurich.insurance_management.requests.ClientRequest;
import com.zurich.insurance_management.responses.ClientResponse;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repository;

	@Override
	public List<ClientResponse> getClientList() {
		List<Client> clients = this.repository.findAll();
		return ClientMapper.entitiesToResponses(clients);
	}

	@Override
	public ClientResponse getClient(String id) throws GeneralControlledException {
		Long idNumber = Long.valueOf(id);
		Optional<Client> client = this.repository.findById(idNumber);
		if (client.isEmpty()) {
			throw new GeneralControlledException("No hemos encontrado el cliente solicitado");
		}
		return ClientMapper.entityToResponse(client.get());
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public CommonResponse createClient(ClientRequest request) throws GeneralControlledException {
		Long newId = this.generateUniqueId();
		if (newId == null) {
			throw new GeneralControlledException("Por el momento no podemos crear nuevos clientes");
		}
		boolean existEmail = this.repository.existsByEmail(request.getEmail());
		if (existEmail) {
			throw new GeneralControlledException("Lo sentimos, este email ya existe");
		}
		Client client = ClientMapper.requestToEntity(request);
		client.setId(newId);
		Client result = this.repository.save(client);
		return new CommonResponse(result != null);
	}

	@Override
	public CommonResponse updateClient(ClientRequest request, boolean forzeId) throws GeneralControlledException {
		Long idNumber = Long.valueOf(request.getId());
		boolean exist = this.repository.existsById(idNumber);
		if (!exist && !forzeId) {
			throw new GeneralControlledException("Por el momento no hemos encontrado el cliente por actualizar");
		}
		boolean existIdEmail = this.repository.existsByEmailAndNotId(request.getEmail(), idNumber);
		if (existIdEmail) {
			throw new GeneralControlledException("Lo sentimos, este email ya existe");
		}
		Client client = ClientMapper.requestToEntity(request);
		Client result = this.repository.save(client);
		return new CommonResponse(result != null);
	}

	@Override
	public CommonResponse deleteClient(String id) throws GeneralControlledException {
		Long idNumber = Long.valueOf(id);
		this.repository.deleteById(idNumber);
		return new CommonResponse(true);
	}

	private Long generateUniqueId() {
		Random rand = new Random();
		Long id = null;
		boolean exists = true;
		int tries = 0;

		while (exists) {
			id = 1000000000L + (long) (rand.nextDouble() * 8999999999L);
			exists = repository.existsById(id);
			if (exists && tries >= 3) {
				exists = false;
			} else if (exists) {
				tries++;
			}
		}

		return id;
	}

}
