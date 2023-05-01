package com.studentsystem.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.studentsystem.customResponse.CustomResponse;
import com.studentsystem.customqueryresult.StudentPaymentsInfo;
import com.studentsystem.customqueryresult.StudentCourseInfo;
import com.studentsystem.customqueryresult.StudentGradesInfo;


@FeignClient(name = "email-sender", path = "email-sender-api/v1/")
@Component
public interface EmailSenderFeingClients {

    @GetMapping("EmailStudentSaved/{name}/{document}/{email}")
    ResponseEntity<CustomResponse> sendEmailStudentSaved(@PathVariable("name") String name,@PathVariable("document") String document,@PathVariable("email") String email);

    @GetMapping("EmailStudentDeleted/{name}/{email}")
    ResponseEntity<CustomResponse> sendStudentDeleteEmail(@PathVariable("name") String name,@PathVariable("email") String email);

    @GetMapping("EmailCourseCancel")
    ResponseEntity<CustomResponse> sendCancelCourseEmail(@RequestBody StudentCourseInfo studentCourseInfo);

    @GetMapping("EmailFullInfoCourse")
    ResponseEntity<CustomResponse> sendfullInfoCourseEmail(@RequestBody StudentCourseInfo studentCourseInfo);

    @GetMapping("EmailReceiptInfoPayment")
    ResponseEntity<CustomResponse> sendReceiptInfoPaymentEmail(@RequestBody StudentPaymentsInfo studentPaymentsInfo);
    
    @GetMapping("EmailSendGradeInfo")
    ResponseEntity<CustomResponse> sendGradeInfoEmail(@RequestBody StudentGradesInfo studentGradesInfo);

}
