package com.microservices.service;

import java.util.Date;

public interface BlacklistedTokenService {

	void cleanupExpiredTokens();

	void blacklistToken(String token, Date expiryDate);

	boolean isTokenBlacklisted(String token);

}
