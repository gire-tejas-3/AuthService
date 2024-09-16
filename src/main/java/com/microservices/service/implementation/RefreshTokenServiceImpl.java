package com.microservices.service.implementation;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.model.RefreshToken;
import com.microservices.model.Users;
import com.microservices.repository.RefreshTokenRepository;
import com.microservices.repository.UserRepository;
import com.microservices.service.JwtService;
import com.microservices.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private JwtService jwtService;

	@Override
	public RefreshToken createRefreshToken(String username) throws Exception {
		Users user = userRepository.findByUsername(username);

		if (user == null)
			throw new Exception("User Not Found with username: " + username);

		RefreshToken refreshToken = user.getRefreshToken();

		if (refreshToken == null) {
			refreshToken = new RefreshToken();
			refreshToken.setUser(user);
			refreshToken.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 8));
			refreshToken.setRefreshToken(UUID.randomUUID().toString());

			refreshTokenRepository.save(refreshToken);
		}

		return refreshToken;
	}

	@Override
	public RefreshToken verifyRefreshToken(String refreshToken) throws Exception {
		RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken);

		if (refToken == null)
			throw new Exception("Refresh Token not Found");

		if (jwtService.isTokenExpired(refreshToken)) {
			refreshTokenRepository.delete(refToken);
			throw new Exception("RefreshToken Expired!");
		}

		return refToken;
	}
	
}
