package com.microservices.utils;

import com.microservices.model.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RegisterRequest {

	private String name;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String dateOfBirth;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	public RegisterRequest() {
		super();
	}

	public RegisterRequest(String name, String username, String password, String email, String phone,
			String dateOfBirth, UserRole role) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
