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
import com.studenthome.emailsender.objHelper.StudentGradesInfo;

@Service
public class GradeEmailSenderService {

	@Autowired
	private EmailFactory emailFactory;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	PdfGeneratorFeingClients pdfGeneratorFeingClients;
	
	public ResponseEntity<CustomResponse> gradeInfo(StudentGradesInfo studentGradesInfo) {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		try {

			CustomResponse customResp = new CustomResponse();

			if (studentGradesInfo.getStudent_email() == null) {

				customResp.setMessage("Data sent is empty");
				customResp.setStatusCode(400);
				customResp.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResp, HttpStatus.BAD_REQUEST);
			}
				
				// Generate email with the generated PDF as attachment
				Context context = new Context();
				context.setVariable("course", studentGradesInfo.getCourse_name());
				context.setVariable("teacher", studentGradesInfo.getTeacher_name());
				context.setVariable("first", studentGradesInfo.getFirst_note());
				context.setVariable("second", studentGradesInfo.getSecond_note());
				context.setVariable("third", studentGradesInfo.getThird_note());
				context.setVariable("additional", studentGradesInfo.getAdditional_note());
				context.setVariable("final", studentGradesInfo.getFinal_note());
				context.setVariable("status", studentGradesInfo.getStatus());
				context.setVariable("start", studentGradesInfo.getClassroom_start_date());
				context.setVariable("end", studentGradesInfo.getClassroom_end_date());

				String htmlBody = templateEngine.process("grade/GradeInfo.html", context);

				emailFactory.emailGenerator(studentGradesInfo.getStudent_email(), studentGradesInfo.getCourse_name()+" Grade Info - DON'T REPLY", htmlBody, null, null);

				customResp.setMessage("Email sent with success");
				customResp.setStatusCode(200);
				customResp.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResp, HttpStatus.OK);

		} catch (Exception e) {

			CustomResponse ExceptionError = new CustomResponse();
			ExceptionError.setMessage(e.toString());
			ExceptionError.setStatusCode(500);
			ExceptionError.setTimestamp(formattedDateTime);

			return new ResponseEntity<>(ExceptionError, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
