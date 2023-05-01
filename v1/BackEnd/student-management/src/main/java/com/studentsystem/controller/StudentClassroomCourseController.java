package com.studentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.customResponse.CustomResponse;
import com.studentsystem.customqueryresult.StudentCourseInfo;
import com.studentsystem.service.StudentClassroomCourseService;

@CrossOrigin("*")
@RestController
@RequestMapping("student-management-api/v1/")
public class StudentClassroomCourseController {

	@Autowired
	StudentClassroomCourseService classroomCourseService;
	
	@GetMapping("getStudentCoursesByStudentId/{student_id}")
	public ResponseEntity<List<StudentCourseInfo>> getStudentCoursesByStudentId(@PathVariable Integer student_id){
		return classroomCourseService.getStudentCoursesByStudentId(student_id);
	}
	
	@GetMapping("getStudentCoursesByCourseNameAndStudentId/{student_id}/{course_name}")
	public ResponseEntity<List<StudentCourseInfo>> getStudentCoursesByCourseNameAndStudentId(@PathVariable Integer student_id, @PathVariable String course_name){
		return classroomCourseService.getStudentCoursesByCourseNameAndStudentId(student_id,course_name);
	}
	
	@GetMapping("cancelStudentCourse/{studentClassroomCourse_id}")
	public ResponseEntity<CustomResponse> cancelStudentCourse(@PathVariable Integer studentClassroomCourse_id){
		return classroomCourseService.cancelStudentCourse(studentClassroomCourse_id);
	}
	
	@GetMapping("getFullCourseAndClassroomInfo/{studentClassroomCourse_id}")
	public ResponseEntity<List<StudentCourseInfo>> getAllCourseInfoByStudentClassroomCourseId(@PathVariable Integer studentClassroomCourse_id){
		return classroomCourseService.getAllCourseInfoByStudentClassroomCourseId(studentClassroomCourse_id);
	}
	
	@GetMapping("getAllInfoOfCoursesByStudentClassroomCourseIdAndSendByEmail/{studentClassroomCourse_id}")
	public ResponseEntity<CustomResponse> getAllInfoOfCoursesByStudentClassroomCourseIdAndSendByEmail(@PathVariable Integer studentClassroomCourse_id){
		return classroomCourseService.getAllCourseInfoByStudentClassroomCourseIdAndSendByEmail(studentClassroomCourse_id);
	}
}
