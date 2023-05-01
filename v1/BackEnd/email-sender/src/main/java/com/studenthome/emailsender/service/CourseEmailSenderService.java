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
import com.studenthome.emailsender.objHelper.StudentCourseInfo;

@Service
public class CourseEmailSenderService {

	@Autowired
	private EmailFactory emailFactory;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	PdfGeneratorFeingClients pdfGeneratorFeingClients;
	
	public ResponseEntity<CustomResponse> courseDeleteEmail(StudentCourseInfo studentCourseInfo) {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		try {

			CustomResponse customResp = new CustomResponse();

			if (studentCourseInfo.getCourse_name() == null) {

				customResp.setMessage("Data sended was null");
				customResp.setStatusCode(400);
				customResp.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResp, HttpStatus.BAD_REQUEST);
			}

			// Generate email with the generated PDF as attachment
			Context context = new Context();
			context.setVariable("course", studentCourseInfo.getCourse_name());
			context.setVariable("price", studentCourseInfo.getCourse_price());

			String htmlBody = templateEngine.process("studentCourseClassroom/courseCancel.html", context);

			emailFactory.emailGenerator(studentCourseInfo.getStudent_email(), "Course " + studentCourseInfo.getCourse_name() + " canceled - DON'T REPLY", htmlBody,
					null, null);

			customResp.setMessage("Email sended with success");
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

	public ResponseEntity<CustomResponse> sendfullInfoCourseEmail(StudentCourseInfo courseFullInfo) {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		try {

			CustomResponse customResp = new CustomResponse();

			if (courseFullInfo.getStudent_email() == null) {

				customResp.setMessage("Data sent was empty");
				customResp.setStatusCode(400);
				customResp.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResp, HttpStatus.BAD_REQUEST);
			}

			// Generate email with the generated PDF as attachment
			Context context = new Context();
			context.setVariable("course", courseFullInfo.getCourse_name());
			context.setVariable("modality", courseFullInfo.getCourse_modality());
			context.setVariable("field", courseFullInfo.getCourse_field());
			context.setVariable("price", courseFullInfo.getCourse_price());
			context.setVariable("teacher", courseFullInfo.getTeacher_name());
			context.setVariable("period", courseFullInfo.getClassroom_period());
			context.setVariable("start_time", courseFullInfo.getClassroom_start_time());
			context.setVariable("end_time", courseFullInfo.getClassroom_end_time());
			context.setVariable("start_date", courseFullInfo.getClassroom_start_date());
			context.setVariable("end_date", courseFullInfo.getClassroom_end_date());

			String htmlBody = templateEngine.process("studentCourseClassroom/fullCourseInfo.html", context);

			emailFactory.emailGenerator(courseFullInfo.getStudent_email(), courseFullInfo.getCourse_name()+" Course Informations - DON'T REPLY", htmlBody, null, null);

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
