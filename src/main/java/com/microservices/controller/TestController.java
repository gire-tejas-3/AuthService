package com.microservices.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.model.Users;
import com.microservices.repository.UserRepository;
import com.microservices.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/home")
public class TestController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<List<Users>>(userRepository.findAll(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/email")
	public String sendEmail(@RequestParam String to, @RequestParam String toName) throws MessagingException, IOException {
		emailService.send(to, "Microservice- Email Service", "Please verify this OTP with Application", toName);
		return "Email Sent";
	}
}
