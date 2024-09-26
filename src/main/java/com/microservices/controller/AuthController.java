package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.service.AuthService;
import com.microservices.utils.AuthResponse;
import com.microservices.utils.LoginRequest;
import com.microservices.utils.RegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) throws Exception {
		return new ResponseEntity<AuthResponse>(authService.register(registerRequest), HttpStatus.CREATED);
	}

	@PostMapping("/login") 
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
		return new ResponseEntity<AuthResponse>(authService.login(loginRequest), HttpStatus.OK);
	}
}
