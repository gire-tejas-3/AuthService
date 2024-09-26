package com.microservices.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ForgotPassword")
public class ForgotPassword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer otp;
	private Date expiration;

	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Users user;

	public ForgotPassword() {
	}

	public ForgotPassword(Integer id, Integer otp, Date expiration, Users user) {
		super();
		this.id = id;
		this.otp = otp;
		this.expiration = expiration;
		this.user = user;
	}

	public ForgotPassword(ForgotPassword forgotPassword) {
	}

	public ForgotPassword setId(Integer id) {
		this.id = id;
		return this;
	}

	public ForgotPassword setOtp(Integer otp) {
		this.otp = otp;
		return this;
	}

	public ForgotPassword setExpiration(Date expiration) {
		this.expiration = expiration;
		return this;
	}

	public ForgotPassword setUser(Users user) {
		this.user = user;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public Integer getOtp() {
		return otp;
	}

	public Date getExpiration() {
		return expiration;
	}

	public Users getUser() {
		return user;
	}

	public ForgotPassword build() {
		return new ForgotPassword(this);
	}

}
