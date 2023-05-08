package com.studentsystem.controller;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.customqueryresult.StudentPaymentsInfo;
import com.studentsystem.objects.CustomResponse;
import com.studentsystem.service.PaymentService;

@RestController
@PermitAll
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("student-management-api/v1/")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@GetMapping("getPaymentsByStudentId/{student_id}")
	public ResponseEntity<List<StudentPaymentsInfo>> getPaymentsByStudentId(@PathVariable Integer student_id){
		return paymentService.getPaymentsByStudentId(student_id);
	}
	
	@GetMapping("getPaymentsByStudentIdAndReceipDate/{student_id}/{from}/{until}")
	public ResponseEntity<List<StudentPaymentsInfo>> getPaymentsByStudentIdAndReceipDate(@PathVariable Integer student_id, @PathVariable String from, @PathVariable String until){
		return paymentService.getPaymentsByStudentIdAndReceipDate(student_id, from, until);
	}
	
	@GetMapping("getPaymentsByIdAndSendByEmail/{payment_id}")
	public ResponseEntity<CustomResponse> getPaymentsByIdAndSendByEmail(@PathVariable Integer payment_id){
		return paymentService.getPaymentByIdAndSendByEmail(payment_id);
	}
	
}
