package com.studenthome.emailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studenthome.emailsender.customResponse.CustomResponse;
import com.studenthome.emailsender.service.StudentEmailSenderService;

@RestController
@RequestMapping(value = "email-sender-api/v1/", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class StudentEmailSenderController {
	
	@Autowired
	StudentEmailSenderService studentEmailSenderService;

    @GetMapping("EmailStudentSaved/{name}/{document}/{email}")
    public ResponseEntity<CustomResponse> sendEmailStudentSaved(@PathVariable("name") String name,@PathVariable("document") String document,@PathVariable("email") String email) {
    	return studentEmailSenderService.studentCreationEmail(name, document, email);
    }
    
    @GetMapping("EmailStudentDeleted/{name}/{email}")
    public ResponseEntity<CustomResponse> sendStudentDeleteEmail(@PathVariable("name") String name,@PathVariable("email") String email) {
        return studentEmailSenderService.studentDeleteEmail(name, email);
    }
}
