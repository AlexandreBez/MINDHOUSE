package com.studenthome.FileGenerator.controller;

import com.lowagie.text.DocumentException;
import com.studenthome.FileGenerator.customResponse.CustomResponse;
import com.studenthome.FileGenerator.objHelper.StudentPaymentsInfo;
import com.studenthome.FileGenerator.service.PdfGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "file-generator-api/v1/", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class PdfController {

    @Autowired
    PdfGeneratorService pdfGeneratorService;

    @GetMapping("studentServiceTerms/{name}/{document}/{email}")
    public ResponseEntity<CustomResponse> generateServiceAggrementPdf(@PathVariable("name") String name,@PathVariable("document") String document,@PathVariable("email") String email) throws DocumentException, IOException{
    	return pdfGeneratorService.generateServiceAggrementPdf(name, document, email);
    }
    
    @GetMapping("paymentReceiptInfo")
    ResponseEntity<CustomResponse> generatePaymentReceiptInfo(@RequestBody StudentPaymentsInfo studentPaymentsInfo)  throws DocumentException, IOException{
    	return pdfGeneratorService.generatePaymentReceiptInfo(studentPaymentsInfo);
    }
}
