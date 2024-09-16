package com.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.model.ForgotPassword;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {

}
