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
@Table(name = "refreshToken")
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String refreshToken;
	private Date expiration;

	@OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference 
    private Users user;

	public RefreshToken() {
	}

	public RefreshToken(String refreshToken, Date expiration, Users user) {
		super();
		this.refreshToken = refreshToken;
		this.expiration = expiration;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RefreshToken [id=" + id + ", refreshToken=" + refreshToken + ", expiration=" + expiration + ", user="
				+ user + "]";
	}

}
