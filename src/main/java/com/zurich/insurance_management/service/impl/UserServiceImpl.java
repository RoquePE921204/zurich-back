package com.zurich.insurance_management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zurich.insurance_management.custom.exceptions.GeneralControlledException;
import com.zurich.insurance_management.custom.mappers.ClientMapper;
import com.zurich.insurance_management.entities.Client;
import com.zurich.insurance_management.entities.User;
import com.zurich.insurance_management.repository.ClientRepository;
import com.zurich.insurance_management.repository.UserRepository;
import com.zurich.insurance_management.requests.LoginRequest;
import com.zurich.insurance_management.responses.UserResponse;
import com.zurich.insurance_management.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public UserResponse login(LoginRequest request) throws GeneralControlledException {
		Optional<User> user = this.repository.findByCredentials(request.getUser(), request.getPassword());
		if (user.isEmpty()) {
			throw new GeneralControlledException("Usuario o contraseña incorrectos");
		}
		UserResponse response = new UserResponse();
		response.setRole(user.get().getRole());
		Optional<Client> client = this.clientRepository.findByIdWithLock(user.get().getId());
		if (client.isPresent()) {
			response.setClient(ClientMapper.entityToResponse(client.get()));
		}
		return response;
	}

}
