package com.studentsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.customResponse.CustomResponse;
import com.studentsystem.model.entity.Student;
import com.studentsystem.service.StudentService;

@CrossOrigin("*")
@RestController
@RequestMapping("student-management-api/v1/")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("getStudentById/{id}")
	public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Integer id){
		return studentService.getStudentById(id);
	}
	
	@GetMapping("searchStudentByName/{name}")
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name){
		return studentService.getStudentByName(name);
	}
	
	@GetMapping("searchStudentByDocument/{document}")
	public ResponseEntity<List<Student>> getStudentByDocument(@PathVariable String document){
		return studentService.getStudentByDocument(document);
	}
	
	@DeleteMapping("deleteStudent/{id}")
	public ResponseEntity<CustomResponse> deleteStudent(@PathVariable Integer id){
		return studentService.deleteStudentById(id);
	}
	
	@PutMapping("updatedStudent/{id}")
	public ResponseEntity<CustomResponse> updatedStudent(@RequestBody Student student,@PathVariable Integer id){
		return studentService.updatedStudent(id, student);
	}
	
	@PostMapping("addNewStudent")
	public ResponseEntity<CustomResponse> addNewStudent(@RequestBody Student student){
		return studentService.saveNewStudent(student);
	}
	
}
