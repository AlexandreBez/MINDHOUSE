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

import com.studentsystem.customqueryresult.StudentGradesInfo;
import com.studentsystem.objects.CustomResponse;
import com.studentsystem.service.GradeService;

@RestController
@PermitAll
@CrossOrigin(origins = "http://localhost:4200")
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
