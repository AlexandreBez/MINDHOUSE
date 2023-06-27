package com.emailServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.emailServer.factory.EmailFactory;
import com.emailServer.objects.CustomResponse;

@RestController
public class testTemplates {
	
	@Autowired
	EmailFactory emailFactory;

	@Autowired
	TemplateEngine templateEngine;

	@GetMapping("testTemplate/{email}")
	ResponseEntity<CustomResponse> testTemplate(@PathVariable("email") String email){
		try {
			CustomResponse customResponse = new CustomResponse();
			
			// Generate email with the generated File
			Context context = new Context();
			String htmlBody = templateEngine.process("test/test", context);
			
			// Get filename in the Path String
			emailFactory.emailGenerator(email,"TEST TEMPLATE", htmlBody,null, null);
			
			customResponse.setMessage("Email sent...");
			customResponse.setStatusCode(200);
			customResponse.setTimestamp("test date");
			
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse();
			customResponse.setMessage(e.getMessage());
			customResponse.setStatusCode(500);
			customResponse.setTimestamp("test date");
			return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
