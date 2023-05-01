package com.studenthome.emailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studenthome.emailsender.customResponse.CustomResponse;
import com.studenthome.emailsender.objHelper.StudentCourseInfo;
import com.studenthome.emailsender.service.CourseEmailSenderService;

@RestController
@RequestMapping(value = "email-sender-api/v1/", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class CourseEmailSenderController {

    @Autowired
    CourseEmailSenderService courseEmailSenderService;
    
    @GetMapping("EmailCourseCancel")
    ResponseEntity<CustomResponse> sendCancelCourseEmail(@RequestBody StudentCourseInfo studentCourseInfo){
    	return courseEmailSenderService.courseDeleteEmail(studentCourseInfo);
    }
    
    @GetMapping("EmailFullInfoCourse")
    ResponseEntity<CustomResponse> sendfullInfoCourseEmail(@RequestBody StudentCourseInfo studentCourseInfo){
    	return courseEmailSenderService.sendfullInfoCourseEmail(studentCourseInfo);
    }
    
}
