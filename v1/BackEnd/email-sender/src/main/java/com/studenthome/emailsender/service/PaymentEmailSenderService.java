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
import com.studenthome.emailsender.objHelper.StudentPaymentsInfo;

@Service
public class PaymentEmailSenderService {

	@Autowired
	private EmailFactory emailFactory;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	PdfGeneratorFeingClients pdfGeneratorFeingClients;
	
	public ResponseEntity<CustomResponse> paymentReceiptInfo(StudentPaymentsInfo studentPaymentsInfo) {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		try {

			CustomResponse customResp = new CustomResponse();

			if (studentPaymentsInfo.getStudent_email() == null) {

				customResp.setMessage("Data sent is empty");
				customResp.setStatusCode(400);
				customResp.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResp, HttpStatus.BAD_REQUEST);
			}

			customResp = pdfGeneratorFeingClients.generatePaymentReceiptInfo(studentPaymentsInfo).getBody();
			
			if (customResp.getStatusCode() == 201) {
				
				// Generate email with the generated PDF as attachment
				Context context = new Context();
				context.setVariable("course", studentPaymentsInfo.getCourse_name());
				context.setVariable("price", studentPaymentsInfo.getCourse_price());
				context.setVariable("promotionName", studentPaymentsInfo.getPromotion_name());
				context.setVariable("descountAmount", studentPaymentsInfo.getPromotion_descount_amount());
				context.setVariable("total", studentPaymentsInfo.getPayment_total_amount());
				context.setVariable("method", studentPaymentsInfo.getPayment_method());
				context.setVariable("status", studentPaymentsInfo.getPayment_status());
				context.setVariable("date", studentPaymentsInfo.getPayment_creation_date());

				String htmlBody = templateEngine.process("payment/PaymentReceiptInfo.html", context);

				emailFactory.emailGenerator(studentPaymentsInfo.getStudent_email(), studentPaymentsInfo.getCourse_name()+" Course Receipt Info - DON'T REPLY", htmlBody, customResp.getMessage(), studentPaymentsInfo.getPayment_id()+"Receipt.pdf");

				customResp.setMessage("Email sent with success");
				customResp.setStatusCode(200);
				customResp.setTimestamp(formattedDateTime);

				return new ResponseEntity<>(customResp, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(customResp, HttpStatus.INTERNAL_SERVER_ERROR);
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
