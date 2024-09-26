package com.microservices.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.microservices.model.Users;
import com.microservices.repository.RefreshTokenRepository;
import com.microservices.repository.UserRepository;
import com.microservices.service.BlacklistedTokenService;
import com.microservices.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutHandlers implements LogoutHandler {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private BlacklistedTokenService blacklistedTokenService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		final String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer ")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String token = header.substring(7);
		String username = jwtService.extractUsername(token);

		Users user = userRepository.findByUsername(username);

		if (!jwtService.isValidToken(token, user)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized if token is not valid
			return;
		}

		if (user != null) {
			refreshTokenRepository.delete(user.getRefreshToken());
			blacklistedTokenService.blacklistToken(token, new Date(System.currentTimeMillis()));
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

}
