package com.studenthome.emailsender.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.studenthome.emailsender.customResponse.CustomResponse;
import com.studenthome.emailsender.objHelper.StudentPaymentsInfo;

@Component
@FeignClient(name = "file-generator", path = "file-generator-api/v1/")
public interface PdfGeneratorFeingClients {
	
    @GetMapping("studentServiceTerms/{name}/{document}/{email}")
    ResponseEntity<CustomResponse> generateServiceAggrementPdf(@PathVariable("name") String name,@PathVariable("document") String document,@PathVariable("email") String email);
    
    @GetMapping("paymentReceiptInfo")
    ResponseEntity<CustomResponse> generatePaymentReceiptInfo(@RequestBody StudentPaymentsInfo studentPaymentsInfo);
    
}
