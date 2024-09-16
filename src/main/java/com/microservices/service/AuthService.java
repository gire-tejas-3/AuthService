package com.microservices.service;

import com.microservices.model.Users;
import com.microservices.utils.AuthResponse;
import com.microservices.utils.LoginRequest;
import com.microservices.utils.RegisterRequest;

public interface AuthService {

	AuthResponse login(LoginRequest loginRequest) throws Exception;

	AuthResponse register(RegisterRequest registerRequest) throws Exception;

	Users getLoggedInUser();

}
