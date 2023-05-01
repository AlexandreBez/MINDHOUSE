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
import com.studentsystem.customqueryresult.StudentGradesInfo;
import com.studentsystem.service.GradeService;

@RestController
@RequestMapping("student-management-api/v1/")
public class GradeController {
	
	@Autowired
	GradeService gradeService;

	@GetMapping("getStudentGradesByStudentId/{id}")
	public ResponseEntity<List<StudentGradesInfo>> getStudentGradesByStudentId(@PathVariable Integer id){
		return gradeService.getStudentGradesByStudentId(id);
	}
	
	@GetMapping("getStudentGradesByStudentIdAndCourseName/{id}/{name}")
	public ResponseEntity<List<StudentGradesInfo>> getStudentGradesByStudentIdAndCourseName(@PathVariable Integer id,@PathVariable String name){
		return gradeService.getStudentGradesByStudentIdAndCourseName(id, name);
	}
	
	@GetMapping("getStudentGradesByGradeIdAndSendEmailWithInfo/{id}")
	public ResponseEntity<CustomResponse> getStudentGradesByStudentIdAndSendEmailWithInfo(@PathVariable Integer id){
		return gradeService.getStudentGradesByStudentIdAndSendEmailWithInfo(id);
	}
}
