package com.studenthome.emailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studenthome.emailsender.customResponse.CustomResponse;
import com.studenthome.emailsender.objHelper.StudentPaymentsInfo;
import com.studenthome.emailsender.service.PaymentEmailSenderService;

@RestController
@RequestMapping(value = "email-sender-api/v1/", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class PaymentEmailSenderController {

	@Autowired
	PaymentEmailSenderService paymentEmailSenderService;
	
    @GetMapping("EmailReceiptInfoPayment")
    ResponseEntity<CustomResponse> sendReceiptInfoPaymentEmail(@RequestBody StudentPaymentsInfo studentPaymentsInfo){
    	return paymentEmailSenderService.paymentReceiptInfo(studentPaymentsInfo);
    }

}
