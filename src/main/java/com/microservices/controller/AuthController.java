package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.model.RefreshToken;
import com.microservices.model.Users;
import com.microservices.service.AuthService;
import com.microservices.service.JwtService;
import com.microservices.service.RefreshTokenService;
import com.microservices.utils.AuthResponse;
import com.microservices.utils.LoginRequest;
import com.microservices.utils.RefreshTokenRequest;
import com.microservices.utils.RegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) throws Exception {
		return new ResponseEntity<AuthResponse>(authService.register(registerRequest), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
		return new ResponseEntity<AuthResponse>(authService.login(loginRequest), HttpStatus.OK);
	}

	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
			throws Exception {
		RefreshToken verifiedRefreshToken = refreshTokenService
				.verifyRefreshToken(refreshTokenRequest.getRefreshToken());

		Users user = verifiedRefreshToken.getUser();
		String accessToken = jwtService.generateToken(user);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setAccessToken(accessToken);
		authResponse.setRefreshToken(verifiedRefreshToken.getRefreshToken());
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}
}
