package com.studentsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.studentsystem.model.CourseJPA;
import com.studentsystem.model.entity.Course;

import javax.transaction.Transactional;

@Service
public class CourseService {

	@Autowired
	CourseJPA courseRepository;
	
	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<List<Course>> getAllCourses() {

		try {

			List<Course> listOfCourses = courseRepository.findAll();

			if (listOfCourses.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(listOfCourses, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Optional<Course>> getCourseById(Integer id) {

		try {

			Optional<Course> listOfCourses = courseRepository.findById(id);

			if (listOfCourses.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(listOfCourses, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Course>> getCourseByName(String name) {

		try {

			List<Course> listOfCourses = courseRepository.findCourseByName(name);

			if (listOfCourses.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(listOfCourses, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Course>> getCourseByField(String field) {

		try {

			List<Course> listOfCourses = courseRepository.findCourseByField(field);

			if (listOfCourses.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(listOfCourses, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Course>> getCourseByModality(String modality) {

		try {

			List<Course> listOfCourses = courseRepository.findCourseByModality(modality);

			if (listOfCourses.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(listOfCourses, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<HttpStatus> deleteCourseById(Integer id) {
		try {

			Optional<Course> listOfCourses = courseRepository.findById(id);

			if (listOfCourses.isPresent()) {
				courseRepository.deleteById(id);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<Course> updatedCourse(Integer id, Course course) {
		try {

			Optional<Course> listOfCourses = courseRepository.findById(id);

			if (listOfCourses.isPresent()) {

				Course courseForUpdated = listOfCourses.get();

				courseForUpdated.setCourse_name(course.getCourse_name());
				courseForUpdated.setCourse_field(course.getCourse_field());
				courseForUpdated.setCourse_price(course.getCourse_price());
				courseForUpdated.setCourse_modality(course.getCourse_modality());

				courseRepository.save(courseForUpdated);

				return new ResponseEntity<>(null, HttpStatus.OK);
			}

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<Course> saveNewCourse(Course course) {
		try {
			courseRepository.save(course);
			return new ResponseEntity<>(null, HttpStatus.CREATED);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
