package com.emailServer.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.emailServer.factory.EmailFactory;
import com.emailServer.objects.CustomResponse;

@Service
public class UserToolsService {
	
	@Autowired
	EmailFactory emailFactory;

	@Autowired
	TemplateEngine templateEngine;

	public ResponseEntity<CustomResponse> sendEmailWithToken(String email, String token){

		CustomResponse response = new CustomResponse();
		try {
			
			if (email.isEmpty()) {
				response.setMessage("Data sent is empty...");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			// Generate email with the generated File
			Context context = new Context();
			context.setVariable("token", token);
			String htmlBody = templateEngine.process("UserTools/SendToken", context);

			// Get filename in the Path String
			emailFactory.emailNoReplyGenerator(email,"Mindhouse account reset token",htmlBody,null, null);

			response.setMessage("Email sended with success");
			response.setStatusCode(200);
			response.setTimestamp(new Date().toString());

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
