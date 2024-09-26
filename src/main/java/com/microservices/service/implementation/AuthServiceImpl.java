package com.microservices.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.model.RefreshToken;
import com.microservices.model.UserRole;
import com.microservices.model.Users;
import com.microservices.repository.UserRepository;
import com.microservices.service.AuthService;
import com.microservices.service.JwtService;
import com.microservices.service.RefreshTokenService;
import com.microservices.utils.AuthResponse;
import com.microservices.utils.LoginRequest;
import com.microservices.utils.RegisterRequest;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthResponse register(RegisterRequest registerRequest) throws Exception {
		Users user = new Users();
		user.setName(registerRequest.getName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setPhone(registerRequest.getPhone());
		user.setDateOfBirth(registerRequest.getDateOfBirth());
		user.setUsername(registerRequest.getUsername());
		user.setRole(UserRole.USER);

		Users registeredUser = userRepository.save(user);

		if (registeredUser == null)
			throw new Exception("User Registration Failed");

		String accessToken = jwtService.generateToken(user);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

		AuthResponse authResponse = new AuthResponse();
		authResponse.setAccessToken(accessToken);
		authResponse.setRefreshToken(refreshToken.getRefreshToken());
		return authResponse;
	}

	@Override
	public AuthResponse login(LoginRequest loginRequest) throws Exception {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		Users user = userRepository.findByUsername(loginRequest.getUsername());
		if (user == null)
			throw new Exception("User Not Found!");

		String accessToken = jwtService.generateToken(user);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());

		AuthResponse authResponse = new AuthResponse();
		authResponse.setAccessToken(accessToken);
		authResponse.setRefreshToken(refreshToken.getRefreshToken());

		return authResponse;
	}

	@Override
	public Users getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = (String) authentication.getPrincipal();

			Users user = userRepository.findByUsername(username);
			return user;
		}
		return null;

	}

}
