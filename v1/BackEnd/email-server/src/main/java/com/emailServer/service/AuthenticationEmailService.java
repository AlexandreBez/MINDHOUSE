package com.emailServer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.emailServer.factory.EmailFactory;
import com.emailServer.objects.CustomResponse;
import com.emailServer.objects.TokenExchange;

@Service
public class AuthenticationEmailService {
	
	@Autowired
	EmailFactory emailFactory;

	@Autowired
	TemplateEngine templateEngine;

	public ResponseEntity<CustomResponse> sendTokenForEmail(TokenExchange tokenExchange){
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {

			CustomResponse responseMsg = new CustomResponse();
			
			if (tokenExchange.getEmail() == null || tokenExchange.getToken() == null) {
				responseMsg.setMessage("Data sent is empty...");
				responseMsg.setStatusCode(400);
				responseMsg.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(responseMsg, HttpStatus.BAD_REQUEST);
			}

			// Generate email with the generated File
			Context context = new Context();
			context.setVariable("token", tokenExchange.getToken());
			String htmlBody = templateEngine.process("login/ResetPasswordToken", context);

			// Get filename in the Path String
			emailFactory.emailGenerator(tokenExchange.getEmail(),"Reset password Token - DON'T REPLY",htmlBody,null, null);

			responseMsg.setMessage("Email sended with success");
			responseMsg.setStatusCode(200);
			responseMsg.setTimestamp(formattedDateTime);

			return new ResponseEntity<>(responseMsg, HttpStatus.OK);

		} catch (Exception e) {
			CustomResponse exceptionMsg = new CustomResponse();
			exceptionMsg.setMessage(e.getMessage());
			exceptionMsg.setStatusCode(500);
			exceptionMsg.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(exceptionMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
