package com.studenthome.FileGenerator.service;

import com.lowagie.text.DocumentException;
import com.studenthome.FileGenerator.customResponse.CustomResponse;
import com.studenthome.FileGenerator.objHelper.StudentPaymentsInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorService {
	
	@Autowired
	TemplateEngine templateEngine;
	
	public ResponseEntity<CustomResponse> generateServiceAggrementPdf(String student_name, String student_document, String student_email) throws DocumentException, IOException {
	    
		LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
	    
	    try {
	    	
	    	CustomResponse response = new CustomResponse();

	    	if(student_name == null || student_document == null || student_email == null) {

	    		response.setMessage("Data received is empty");
	    		response.setStatusCode(400);
	    		response.setTimestamp(LocalDate.now().toString());
	    		
	    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    	}
	    	
	    	String fileName = student_document+"ServiceAgreement.pdf";
	    	String filePath = "C:/Users/Lucas/Desktop/Repositorys/Projetos/StudentManagementSystem/v1/BackEnd/GeneratedDocuments/Students/ServiceTerms";
	        
	        /**
	         * Add in the HTML the values with thymeleaf
	         */
	        Context context = new Context();
	        context.setVariable("studentName", student_name);
	        context.setVariable("date", formattedDateTime);
	        
	        File outputFile = new File(filePath, fileName);

	        if (!outputFile.getParentFile().exists()) {
	            outputFile.getParentFile().mkdirs();
	        }

	        OutputStream os = new FileOutputStream(outputFile);

	        String templatePath = "HtmlTemplates/Students/ServiceTerms/ServiceTerms.html";
	        String htmlTemplate = templateEngine.process(templatePath, context);

	        ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocumentFromString(htmlTemplate);
	        renderer.layout();
	        renderer.createPDF(os);

	        os.flush();
	        os.close();
    		
    		response.setMessage(outputFile.getAbsolutePath());
    		response.setStatusCode(201);
    		response.setTimestamp(formattedDateTime);

        	return new ResponseEntity<>(response, HttpStatus.CREATED);

	    } catch (DocumentException e) {
	    	
        		CustomResponse DocumentExceptionresponse = new CustomResponse();
        		DocumentExceptionresponse.setMessage(e.toString());
        		DocumentExceptionresponse.setStatusCode(500);
        		DocumentExceptionresponse.setTimestamp(formattedDateTime);
    	        return new ResponseEntity<>(DocumentExceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
	        
	    } catch (IOException e) {
	    	
        		CustomResponse IOExceptionresponse = new CustomResponse();
        		IOExceptionresponse.setMessage(e.toString());
        		IOExceptionresponse.setStatusCode(500);
        		IOExceptionresponse.setTimestamp(formattedDateTime);
    	        return new ResponseEntity<>(IOExceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
    	        
	    }
	    
	}
	
	public ResponseEntity<CustomResponse> generatePaymentReceiptInfo(StudentPaymentsInfo studentPaymentsInfo) throws DocumentException, IOException {
	    
		LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
	    
	    try {
	    	
	    	CustomResponse response = new CustomResponse();

	    	if(studentPaymentsInfo.getStudent_email() == null) {

	    		response.setMessage("Data received is empty");
	    		response.setStatusCode(400);
	    		response.setTimestamp(LocalDate.now().toString());
	    		
	    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    	}
	    	
	    	String fileName = studentPaymentsInfo.getPayment_id()+"Receipt.pdf";
	    	String filePath = "C:/Users/Lucas/Desktop/Repositorys/Projetos/StudentManagementSystem/v1/BackEnd/GeneratedDocuments/Students/Payments";
	        
	        /**
	         * Add in the HTML the values with thymeleaf
	         */
	        Context context = new Context();
	        context.setVariable("course", studentPaymentsInfo.getCourse_name());
	        context.setVariable("price", studentPaymentsInfo.getCourse_price());
	        context.setVariable("promotionName", studentPaymentsInfo.getPromotion_name());
	        context.setVariable("descountAmount", studentPaymentsInfo.getPromotion_descount_amount());
	        context.setVariable("total", studentPaymentsInfo.getPayment_total_amount());
	        context.setVariable("status", studentPaymentsInfo.getPayment_status());
	        context.setVariable("method", studentPaymentsInfo.getPayment_method());
	        context.setVariable("date", studentPaymentsInfo.getPayment_creation_date());
	        
	        File outputFile = new File(filePath, fileName);

	        if (!outputFile.getParentFile().exists()) {
	            outputFile.getParentFile().mkdirs();
	        }

	        OutputStream os = new FileOutputStream(outputFile);

	        String templatePath = "HtmlTemplates/Payments/ReceiptInfo/ReceiptInfo.html";
	        String htmlTemplate = templateEngine.process(templatePath, context);

	        ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocumentFromString(htmlTemplate);
	        renderer.layout();
	        renderer.createPDF(os);

	        os.flush();
	        os.close();
    		
    		response.setMessage(outputFile.getAbsolutePath());
    		response.setStatusCode(201);
    		response.setTimestamp(formattedDateTime);

        	return new ResponseEntity<>(response, HttpStatus.CREATED);

	    } catch (DocumentException e) {
	    	
        		CustomResponse DocumentExceptionresponse = new CustomResponse();
        		DocumentExceptionresponse.setMessage(e.toString());
        		DocumentExceptionresponse.setStatusCode(500);
        		DocumentExceptionresponse.setTimestamp(formattedDateTime);
    	        return new ResponseEntity<>(DocumentExceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
	        
	    } catch (IOException e) {
	    	
        		CustomResponse IOExceptionresponse = new CustomResponse();
        		IOExceptionresponse.setMessage(e.toString());
        		IOExceptionresponse.setStatusCode(500);
        		IOExceptionresponse.setTimestamp(formattedDateTime);
    	        return new ResponseEntity<>(IOExceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
    	        
	    }
	    
	}
	
}

