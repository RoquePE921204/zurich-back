package com.zurich.insurance_management.service;

import com.zurich.insurance_management.custom.exceptions.GeneralControlledException;
import com.zurich.insurance_management.requests.LoginRequest;
import com.zurich.insurance_management.responses.UserResponse;

public interface UserService {

	public UserResponse login(LoginRequest request) throws GeneralControlledException;

}
