package com.microservices.service;

import java.io.IOException;

import jakarta.mail.MessagingException;

public interface EmailService {

	void send(String to, String subject, String text, String toName) throws MessagingException, IOException;

}
