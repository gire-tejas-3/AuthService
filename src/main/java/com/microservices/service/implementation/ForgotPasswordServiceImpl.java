package com.microservices.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.repository.ForgotPasswordRepository;
import com.microservices.service.ForgotPasswordService;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private ForgotPasswordRepository forgotPasswordRepository;
	

}
