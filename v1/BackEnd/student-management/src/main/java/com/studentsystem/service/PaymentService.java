package com.studentsystem.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studentsystem.customqueryresult.StudentPaymentsInfo;
import com.studentsystem.feingClients.EmailSenderFeingClients;
import com.studentsystem.model.PaymentJPA;
import com.studentsystem.objects.CustomResponse;

import javax.transaction.Transactional;

@Service
public class PaymentService {
	
	@Autowired
	EmailSenderFeingClients senderFeingClients;

	@Autowired
	PaymentJPA paymentRepository;

	@Transactional
	public ResponseEntity<List<StudentPaymentsInfo>> getPaymentsByStudentId(Integer id) {
		try {

			List<Object[]> results = paymentRepository.getPaymentsByStudentId(id);

			if (results.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			List<StudentPaymentsInfo> payments = new ArrayList<>();

			for (Object[] result : results) {

				StudentPaymentsInfo paymentsInfoResult = new StudentPaymentsInfo();

				paymentsInfoResult.setPayment_id((Integer) result[0]);
				paymentsInfoResult.setCourse_name((String) result[1]);
				paymentsInfoResult.setCourse_price((BigDecimal) result[2]);
				paymentsInfoResult.setPromotion_name((String) result[3]);
				paymentsInfoResult.setPromotion_descount_amount((BigDecimal) result[4]);
				paymentsInfoResult.setPayment_total_amount((BigDecimal) result[5]);
				paymentsInfoResult.setPayment_method((String) result[6]);
				paymentsInfoResult.setPayment_status((String) result[7]);
				paymentsInfoResult.setPayment_creation_date((String) result[8]);
				payments.add(paymentsInfoResult);

			}

			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<List<StudentPaymentsInfo>> getPaymentsByStudentIdAndReceipDate(Integer id, String from, String until) {
		try {

			List<Object[]> results = paymentRepository.getPaymentsByStudentIdAndReceipDate(id, from, until);

			if (results.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			List<StudentPaymentsInfo> payments = new ArrayList<>();

			for (Object[] result : results) {

				StudentPaymentsInfo paymentsInfoResult = new StudentPaymentsInfo();

				paymentsInfoResult.setPayment_id((Integer) result[0]);
				paymentsInfoResult.setCourse_name((String) result[1]);
				paymentsInfoResult.setCourse_price((BigDecimal) result[2]);
				paymentsInfoResult.setPromotion_name((String) result[3]);
				paymentsInfoResult.setPromotion_descount_amount((BigDecimal) result[4]);
				paymentsInfoResult.setPayment_total_amount((BigDecimal) result[5]);
				paymentsInfoResult.setPayment_method((String) result[6]);
				paymentsInfoResult.setPayment_status((String) result[7]);
				paymentsInfoResult.setPayment_creation_date((String) result[8]);
				payments.add(paymentsInfoResult);

			}

			return new ResponseEntity<>(payments, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> getPaymentByIdAndSendByEmail(Integer id) {
		
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {
			
			CustomResponse customResponse = new CustomResponse();
			
			List<Object[]> results = paymentRepository.getPaymentsByIdAndSendByEmail(id);
			
			if (results.isEmpty()) {
				customResponse.setMessage("Data sent is empty");
				customResponse.setStatusCode(500);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			List<StudentPaymentsInfo> payments = new ArrayList<>();

			for (Object[] result : results) {

				StudentPaymentsInfo paymentsInfoResult = new StudentPaymentsInfo();

				paymentsInfoResult.setCourse_name((String) result[0]);
				paymentsInfoResult.setCourse_price((BigDecimal) result[1]);
				paymentsInfoResult.setPromotion_name((String) result[2]);
				paymentsInfoResult.setPromotion_descount_amount((BigDecimal) result[3]);
				paymentsInfoResult.setPayment_total_amount((BigDecimal) result[4]);
				paymentsInfoResult.setPayment_method((String) result[5]);
				paymentsInfoResult.setPayment_status((String) result[6]);
				paymentsInfoResult.setPayment_creation_date((String) result[7]);
				paymentsInfoResult.setStudent_email((String) result[8]);
				payments.add(paymentsInfoResult);

			}
			
			customResponse = senderFeingClients.sendReceiptInfoPaymentEmail(payments.get(0)).getBody();
			
			if(customResponse.getStatusCode() == 200) {
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}else {				
				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			CustomResponse ExceptionResponse = new CustomResponse();
			ExceptionResponse.setMessage(e.getMessage());
			ExceptionResponse.setStatusCode(500);
			ExceptionResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
