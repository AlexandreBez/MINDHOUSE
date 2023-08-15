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
public class ReportToolsService {

	@Autowired
	EmailFactory emailFactory;

	@Autowired
	TemplateEngine templateEngine;

	public ResponseEntity<CustomResponse> sendUserInfoExcelData(FileShareHelper fileShareHelper) {
		CustomResponse response = new CustomResponse();
		try {

			if (fileShareHelper.getEmails() == null || fileShareHelper.getFilePath() == null || fileShareHelper.getFileName() == null) {
				response.setMessage("Email and/or filepath empty...");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			Context context = new Context();
			String htmlBody = templateEngine.process("admin/UserInfoExcel", context);

			for (String recipient : fileShareHelper.getEmails()) {
				emailFactory.emailNoReplyGenerator(recipient, "File generated with success - DON'T REPLY", htmlBody, fileShareHelper.getFilePath(), fileShareHelper.getFileName());
			}

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
