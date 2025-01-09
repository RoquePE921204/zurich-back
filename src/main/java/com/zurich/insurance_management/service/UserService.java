package com.zurich.insurance_management.service;

import com.zurich.insurance_management.custom.exceptions.GeneralControlledException;
import com.zurich.insurance_management.requests.LoginRequest;
import com.zurich.insurance_management.responses.LoginResponse;

public interface UserService {

	public LoginResponse login(LoginRequest request) throws GeneralControlledException;

}
