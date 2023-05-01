package com.studentsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.studentsystem.model.entity.Course;
import com.studentsystem.service.CourseService;

@RestController
@RequestMapping("student-management-api/v1/")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("getAllCourses")
	public ResponseEntity<List<Course>> getAllCourses(){
		return courseService.getAllCourses();
	}
	
	@GetMapping("getCourseById/{id}")
	public ResponseEntity<Optional<Course>> getCourseById(@PathVariable Integer id){
		return courseService.getCourseById(id);
	}
	
	@GetMapping("getCourseByName/{name}")
	public ResponseEntity<List<Course>> getCourseByName(@PathVariable String name){
		return courseService.getCourseByName(name);
	}
	
	@GetMapping("getCourseByField/{field}")
	public ResponseEntity<List<Course>> getCourseByField(@PathVariable String field){
		return courseService.getCourseByField(field);
	}
	
	@DeleteMapping("deleteCourse/{id}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Integer id){
		return courseService.deleteCourseById(id);
	}
	
	@PutMapping("updatedCourse/{id}")
	public ResponseEntity<Course> updatedCourse(@RequestBody Course course,@PathVariable Integer id){
		return courseService.updatedCourse(id, course);
	}
	
	@PostMapping("addNewCourse")
	public ResponseEntity<Course> addNewCourse(@RequestBody Course course){
		return courseService.saveNewCourse(course);
	}
}
