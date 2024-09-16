package com.microservices.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {

	<T> T extractCliam(String token, Function<Claims, T> claimResolver);

	String extractUsername(String token);

	Date extractExpiration(String token);

	boolean isValidToken(String token, UserDetails userDetails);

	boolean isTokenExpired(String token);

	String generateToken(UserDetails userDetails);

}
