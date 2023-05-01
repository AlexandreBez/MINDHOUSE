package com.studentsystem.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studentsystem.customResponse.CustomResponse;
import com.studentsystem.feingClients.EmailSenderFeingClients;
import com.studentsystem.model.StudentJPA;
import com.studentsystem.model.entity.Student;

import javax.transaction.Transactional;

@Service
public class StudentService {
	
	@Autowired
	EmailSenderFeingClients emailSenderFeingClients;

	@Autowired
	StudentJPA studentRepository;

	public ResponseEntity<List<Student>> getAllStudents() {

		try {

			List<Student> listOfStudents = studentRepository.findAll();

			if (listOfStudents.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(listOfStudents, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Optional<Student>> getStudentById(Integer id) {

		try {

			Optional<Student> studentData = studentRepository.findById(id);

			if (studentData.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(studentData, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Student>> getStudentByName(String name) {

		try {

			List<Student> studentData = studentRepository.findStudentByName(name);

			if (studentData.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(studentData, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Student>> getStudentByDocument(String document) {

		try {

			List<Student> studentData = studentRepository.findStudentByDocument(document);

			if (studentData.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(studentData, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> deleteStudentById(Integer id) {
		
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
	    
		try {

			CustomResponse response = new CustomResponse();
					
			Optional<Student> studentData = studentRepository.findById(id);
			
			System.out.println(studentData.get());
			
			if (studentData.isEmpty()) {
				
				response.setMessage("Data sent is empty");
				response.setStatusCode(400);
				response.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			Student studentInfo = studentData.get();
			
			response = emailSenderFeingClients.sendStudentDeleteEmail(studentInfo.getStudent_name(), studentInfo.getStudent_email()).getBody();
			
			if (response.getStatusCode() == 200) {

				studentRepository.deleteById(id);
				response.setMessage("Student deleted with success");
				response.setStatusCode(200);
				response.setTimestamp(formattedDateTime);
				
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			CustomResponse ExceptionResponse = new CustomResponse();
			ExceptionResponse.setMessage(e.getMessage());
			ExceptionResponse.setStatusCode(500);
			ExceptionResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> updatedStudent(Integer id, Student student) {
		
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {

			CustomResponse customResponse = new CustomResponse();
			Optional<Student> studentData = studentRepository.findById(id);

			if (studentData.isPresent()) {

				Student studentForUpdated = studentData.get();

				studentForUpdated.setStudent_name(student.getStudent_name());
				studentForUpdated.setStudent_document(student.getStudent_document());
				studentForUpdated.setStudent_age(student.getStudent_age());
				studentForUpdated.setStudent_birthdate(student.getStudent_birthdate());
				studentForUpdated.setStudent_country(student.getStudent_country());
				studentForUpdated.setStudent_state(student.getStudent_state());
				studentForUpdated.setStudent_city(student.getStudent_city());
				studentForUpdated.setStudent_adress(student.getStudent_adress());
				studentForUpdated.setStudent_zipcode(student.getStudent_zipcode());
				studentForUpdated.setStudent_email(student.getStudent_email());
				studentForUpdated.setStudent_phone(student.getStudent_phone());
				studentForUpdated.setStudent_phone_2(student.getStudent_phone_2());

				studentRepository.save(studentForUpdated);
				
				customResponse.setMessage("Student updated with success");
				customResponse.setStatusCode(200);
				customResponse.setTimestamp(formattedDateTime);
				
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}else {
				customResponse.setMessage("Student not found");
				customResponse.setStatusCode(404);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			CustomResponse ExceptionResponse = new CustomResponse();
			ExceptionResponse.setMessage(e.getMessage());
			ExceptionResponse.setStatusCode(500);
			ExceptionResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> saveNewStudent(Student student) {
		
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
	    
		try {
			
			CustomResponse customResponse = new CustomResponse();
			
			if (student == null) {
				customResponse.setMessage("Data sent is empty");
				customResponse.setStatusCode(400);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
			}
			
			customResponse = emailSenderFeingClients.sendEmailStudentSaved(student.getStudent_name(), student.getStudent_document(), student.getStudent_email()).getBody();
			
		    if(customResponse.getStatusCode() == 200) {
				studentRepository.save(student);
				customResponse.setMessage("Student saved with success");
				return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
		    }else {
		    	return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		    }

		} catch (Exception e) {
			System.out.println(e);
			CustomResponse ExceptionResponse = new CustomResponse();
			ExceptionResponse.setMessage(e.toString());
			ExceptionResponse.setStatusCode(500);
			ExceptionResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}