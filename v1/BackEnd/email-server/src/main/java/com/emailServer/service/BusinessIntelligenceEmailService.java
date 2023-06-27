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
import com.emailServer.objects.FileShareHelper;

@Service
public class BusinessIntelligenceEmailService {

	@Autowired
	EmailFactory emailFactory;

	@Autowired
	TemplateEngine templateEngine;

	public ResponseEntity<CustomResponse> sendUserInfoExcelData(FileShareHelper fileShareHelper) {
		CustomResponse response = new CustomResponse();
		String dateResp = new Date().toString();
		try {

			if (fileShareHelper.getEmail() == null || fileShareHelper.getFilePath() == null) {
				response.setMessage("Email and/or filepath empty...");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			// Generate email with the generated File
			Context context = new Context();
			String htmlBody = templateEngine.process("admin/UserInfoExcel", context);

			// Get filename in the Path String
			emailFactory.emailGenerator(fileShareHelper.getEmail(), "File generated with success - DON'T REPLY", htmlBody, fileShareHelper.getFilePath(), fileShareHelper.getFileName());

			response.setMessage("Email sended with success");
			response.setStatusCode(200);
			response.setTimestamp(dateResp);

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(500);
			response.setTimestamp(dateResp);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
