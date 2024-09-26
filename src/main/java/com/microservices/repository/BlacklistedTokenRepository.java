package com.microservices.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.model.BlacklistedTokens;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedTokens, Long> {

	Optional<BlacklistedTokens> findByToken(String token);

	void deleteByExpiryDateBefore(Date now);
}
