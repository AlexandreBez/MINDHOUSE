package com.studenthome.emailsender.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.studenthome.emailsender.customResponse.CustomResponse;
import com.studenthome.emailsender.emailFactory.EmailFactory;
import com.studenthome.emailsender.feingClients.PdfGeneratorFeingClients;

@Service
public class StudentEmailSenderService {

	@Autowired
	private EmailFactory emailFactory;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	PdfGeneratorFeingClients pdfGeneratorFeingClients;
	
	public ResponseEntity<CustomResponse> studentCreationEmail(String student_name, String student_document,
			String student_email){

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		try {

			CustomResponse customResponse = new CustomResponse();

			if (student_name == null || student_document == null || student_email == null) {

				CustomResponse emptyDataResponse = new CustomResponse();

				emptyDataResponse.setMessage("Data sended was null");
				emptyDataResponse.setStatusCode(400);
				emptyDataResponse.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(emptyDataResponse, HttpStatus.BAD_REQUEST);
			}

			customResponse = pdfGeneratorFeingClients
					.generateServiceAggrementPdf(student_name, student_document, student_email).getBody();

			if (customResponse.getStatusCode() == 201) {
				
				// Generate email with the generated PDF as attachment
				Context context = new Context();
				context.setVariable("studentName", student_name);

				String htmlBody = templateEngine.process("student/StudentCreation.html", context);
				String filePath = customResponse.getMessage();
				String fileName = student_document + "ServiceAgreement.pdf";

				emailFactory.emailGenerator(student_email, "Welcome " + student_name + " - DON'T REPLY", htmlBody,
						filePath, fileName);

				customResponse.setMessage("Email sended with success");
				customResponse.setStatusCode(200);
				customResponse.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResponse, HttpStatus.OK);

			} else {

				customResponse.setMessage("Data received was null or an error occured for generate the Document");
				customResponse.setStatusCode(500);
				customResponse.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);

			}

		} catch (Exception e) {

			CustomResponse ExceptionError = new CustomResponse();
			ExceptionError.setMessage(e.toString());
			ExceptionError.setStatusCode(500);
			ExceptionError.setTimestamp(formattedDateTime);

			return new ResponseEntity<>(ExceptionError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<CustomResponse> studentDeleteEmail(String name, String email) {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		try {

			CustomResponse customResponse = new CustomResponse();

			if (name == null || email == null) {

				customResponse.setMessage("Data sended was null");
				customResponse.setStatusCode(400);
				customResponse.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
			} else {

				// Generate email with the generated PDF as attachment
				Context context = new Context();
				context.setVariable("studentName", name);

				String htmlBody = templateEngine.process("student/StudentCreation.html", context);

				emailFactory.emailGenerator(email, "Good Bye " + name + " - DON'T REPLY", htmlBody,
						null, null);

				customResponse.setMessage("Email sended with success");
				customResponse.setStatusCode(200);
				customResponse.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}

		} catch (Exception e) {

			CustomResponse ExceptionError = new CustomResponse();
			ExceptionError.setMessage(e.toString());
			ExceptionError.setStatusCode(500);
			ExceptionError.setTimestamp(formattedDateTime);

			return new ResponseEntity<>(ExceptionError, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
