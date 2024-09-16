package com.microservices.service.implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.microservices.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.secrete.key}")
	private String secrete;

//	Extract Data from Claims
	@Override
	public <T> T extractCliam(String token, Function<Claims, T> claimResolver) {
		Claims claim = extractClaim(token);
		return claimResolver.apply(claim);
	}

	private Claims extractClaim(String token) {
		return Jwts.parser().verifyWith(secreteKey()).build().parseSignedClaims(token).getPayload();
	}

	private SecretKey secreteKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secrete);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String extractUsername(String token) {
		return extractCliam(token, Claims::getSubject);
	}

	@Override
	public Date extractExpiration(String token) {
		return extractCliam(token, Claims::getExpiration);
	}

	@Override
	public boolean isValidToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	@Override
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date(System.currentTimeMillis()));
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<String, Object>(), userDetails);
	}

	private String generateToken(Map<String, Object> claim, UserDetails userDetails) {
		return Jwts.builder().claims(claim).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
				.issuedAt(new Date(System.currentTimeMillis())).subject(userDetails.getUsername())
				.signWith(secreteKey()).compact();
	}
}
