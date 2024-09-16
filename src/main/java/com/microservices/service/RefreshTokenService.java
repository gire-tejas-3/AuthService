package com.microservices.service;

import com.microservices.model.RefreshToken;

public interface RefreshTokenService {

	RefreshToken createRefreshToken(String username) throws Exception;

	RefreshToken verifyRefreshToken(String refreshToken) throws Exception;

}
