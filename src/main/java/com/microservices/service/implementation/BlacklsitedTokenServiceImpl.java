package com.microservices.service.implementation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.microservices.model.BlacklistedTokens;
import com.microservices.repository.BlacklistedTokenRepository;
import com.microservices.service.BlacklistedTokenService;

@Service
public class BlacklsitedTokenServiceImpl implements BlacklistedTokenService {

	@Autowired
	private BlacklistedTokenRepository blacklistedTokenRepository;

	@Override
	public void blacklistToken(String token, Date expiryDate) {
		BlacklistedTokens blacklistedToken = new BlacklistedTokens();
		blacklistedToken.setToken(token);
		blacklistedToken.setExpiryDate(expiryDate);
		blacklistedTokenRepository.save(blacklistedToken);
	}

	@Override
	public boolean isTokenBlacklisted(String token) {
		return blacklistedTokenRepository.findByToken(token).isPresent();
	}

	@Override
	@Scheduled(cron = "0 0 * * * *")
	public void cleanupExpiredTokens() {
		blacklistedTokenRepository.deleteByExpiryDateBefore(new Date());
	}

}
