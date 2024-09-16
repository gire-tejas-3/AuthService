package com.microservices.service.implementation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.microservices.service.AuthService;
import com.microservices.service.EmailService;
import com.microservices.utils.HtmlTemplateProcessor;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private HtmlTemplateProcessor htmlTemplateProcessor;

	@Autowired
	private AuthService authService;

	@Override
	public void send(String to, String subject, String text, String toName) throws MessagingException, IOException {
		MimeMessage mime = javaMailSender.createMimeMessage();
		MimeMessageHelper msg = new MimeMessageHelper(mime);

		String from = authService.getLoggedInUser().getEmail();
		msg.setFrom(from);
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);
		String html = htmlTemplateProcessor.getProcessedHtml(from, to, subject, text, toName);
		msg.setText(html, true);

		javaMailSender.send(mime);
	}
}
