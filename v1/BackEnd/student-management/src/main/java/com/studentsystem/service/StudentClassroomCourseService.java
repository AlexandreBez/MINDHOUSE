package com.studentsystem.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studentsystem.customResponse.CustomResponse;
import com.studentsystem.customqueryresult.StudentCourseInfo;
import com.studentsystem.feingClients.EmailSenderFeingClients;
import com.studentsystem.model.StudentClassroomCourseJPA;
import com.studentsystem.model.entity.Student_Classroom_Course;

import javax.transaction.Transactional;

@Service
public class StudentClassroomCourseService {

	@Autowired
	EmailSenderFeingClients emailSenderFeingClients;

	@Autowired
	StudentClassroomCourseJPA classroomCourseRepository;

	@Transactional
	public ResponseEntity<List<StudentCourseInfo>> getStudentCoursesByStudentId(Integer id) {

		try {

			List<Object[]> results = classroomCourseRepository.getStudentCoursesByStudentId(id);

			if (results.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {

				List<StudentCourseInfo> courseAndStudentRes = new ArrayList<>();

				for (Object[] result : results) {

					StudentCourseInfo StudentCourseInfo = new StudentCourseInfo();

					StudentCourseInfo.setStudent_class_course_id((Integer) result[0]);
					StudentCourseInfo.setCourse_id((Integer) result[1]);
					StudentCourseInfo.setCourse_name((String) result[2]);
					StudentCourseInfo.setCourse_field((String) result[3]);
					StudentCourseInfo.setCourse_modality((String) result[4]);
					StudentCourseInfo.setClassroom_start_date((String) result[5]);
					StudentCourseInfo.setClassroom_end_date((String) result[6]);
					courseAndStudentRes.add(StudentCourseInfo);

				}

				return new ResponseEntity<>(courseAndStudentRes, HttpStatus.OK);
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<List<StudentCourseInfo>> getStudentCoursesByCourseNameAndStudentId(Integer id, String name) {

		try {

			List<Object[]> results = classroomCourseRepository.getStudentCoursesByCourseNameAndStudentId(id, name);

			if (results.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {

				List<StudentCourseInfo> courseAndStudentRes = new ArrayList<>();

				for (Object[] result : results) {

					StudentCourseInfo studentCourseInfo = new StudentCourseInfo();

					studentCourseInfo.setStudent_class_course_id((Integer) result[0]);
					studentCourseInfo.setCourse_id((Integer) result[1]);
					studentCourseInfo.setCourse_name((String) result[2]);
					studentCourseInfo.setCourse_field((String) result[3]);
					studentCourseInfo.setCourse_modality((String) result[4]);
					studentCourseInfo.setClassroom_start_date((String) result[5]);
					studentCourseInfo.setClassroom_end_date((String) result[6]);
					courseAndStudentRes.add(studentCourseInfo);

				}

				return new ResponseEntity<>(courseAndStudentRes, HttpStatus.OK);
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> cancelStudentCourse(Integer studentClassroomCourse_id) {
		
		LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);

		try {
			
			CustomResponse customResponse = new CustomResponse(); 


			// Retrieve student classroom course by id
			Optional<Student_Classroom_Course> course = classroomCourseRepository.findById(studentClassroomCourse_id);

			// Return 404 NOT_FOUND status if course is not found
			if (course.isEmpty()) {
				
				customResponse.setMessage("Course not found");
				customResponse.setStatusCode(404);
				customResponse.setTimestamp(formattedDateTime);
				
				return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
			}

			// Get start date of student's course
			List<Object[]> results = classroomCourseRepository.getStudentCourseStartDateByStudentClassroomCourseId(studentClassroomCourse_id);
			String dateExtract = null;

			// Extract start date of course
			for (Object[] result : results) {
				dateExtract = (String) result[0];
			}

			LocalDate currentDate = LocalDate.now();
			LocalDate startDate = LocalDate.parse(dateExtract);

			if (currentDate.isAfter(startDate) || currentDate.isEqual(startDate)) {
				
				customResponse.setMessage("The student can't cancel the course since the start date of the course is already in progress");
				customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
				customResponse.setTimestamp(LocalDateTime.now().toString());

				return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
			}
				
			// Course has not yet started
			List<Object[]> resultsForEmailTemplate = classroomCourseRepository.getStudentEmailByStudentClassroomCourseId(studentClassroomCourse_id);
			
			List<StudentCourseInfo> queryResultForDeleteEmailRes = new ArrayList<>();
			
			for (Object[] result : resultsForEmailTemplate) {
				StudentCourseInfo queryResultForDeleteEmail = new StudentCourseInfo();
				queryResultForDeleteEmail.setStudent_name((String) result[0]);
				queryResultForDeleteEmail.setStudent_document((String) result[1]);
				queryResultForDeleteEmail.setStudent_email((String) result[2]);
				queryResultForDeleteEmail.setCourse_name((String) result[3]);
				queryResultForDeleteEmail.setCourse_price((BigDecimal) result[4]);
				queryResultForDeleteEmailRes.add(queryResultForDeleteEmail);

			}

			customResponse = emailSenderFeingClients.sendCancelCourseEmail(queryResultForDeleteEmailRes.get(0)).getBody();
			
			if(customResponse.getStatusCode() == 200) {
				
				classroomCourseRepository.deleteById(studentClassroomCourse_id);
				
				customResponse.setMessage("Course canceled with success");
				customResponse.setStatusCode(200);
				customResponse.setTimestamp(formattedDateTime);
				
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}catch(Exception e){
			CustomResponse ExceptionError = new CustomResponse();
			ExceptionError.setMessage(e.toString());
			ExceptionError.setStatusCode(500);
			ExceptionError.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionError, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public ResponseEntity<List<StudentCourseInfo>> getAllCourseInfoByStudentClassroomCourseId(Integer id) {

		try {

			List<Object[]> results = classroomCourseRepository.getAllCourseInfoByStudentClassroomCourseId(id);

			if (results.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			List<StudentCourseInfo> studentCourseFullInfoRes = new ArrayList<>();

			for (Object[] result : results) {

				StudentCourseInfo studentCourseFullInfo = new StudentCourseInfo();
				studentCourseFullInfo.setStudent_email((String) result[0]);
				studentCourseFullInfo.setStudent_document((String) result[1]);
				studentCourseFullInfo.setCourse_name((String) result[2]);
				studentCourseFullInfo.setCourse_field((String) result[3]);
				studentCourseFullInfo.setCourse_price((BigDecimal) result[4]);
				studentCourseFullInfo.setCourse_modality((String) result[5]);
				studentCourseFullInfo.setClassroom_period((String) result[6]);
				studentCourseFullInfo.setClassroom_start_time((String) result[7]);
				studentCourseFullInfo.setClassroom_end_time((String) result[8]);
				studentCourseFullInfo.setClassroom_start_date((String) result[9]);
				studentCourseFullInfo.setClassroom_end_date((String) result[10]);
				studentCourseFullInfo.setTeacher_name((String) result[11]);
				studentCourseFullInfoRes.add(studentCourseFullInfo);
			}

			return new ResponseEntity<>(studentCourseFullInfoRes, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public ResponseEntity<CustomResponse> getAllCourseInfoByStudentClassroomCourseIdAndSendByEmail(Integer id) {

		LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {

			CustomResponse customResp = new CustomResponse();
			
			List<Object[]> results = classroomCourseRepository.getAllCourseInfoByStudentClassroomCourseId(id);

			if (results.isEmpty()) {
				customResp.setMessage("Data sent was null");
				customResp.setStatusCode(404);
				customResp.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResp, HttpStatus.NOT_FOUND);
			}

			List<StudentCourseInfo> studentCourseFullInfoRes = new ArrayList<>();

			for (Object[] result : results) {

				StudentCourseInfo studentCourseFullInfo = new StudentCourseInfo();

				studentCourseFullInfo.setStudent_email((String) result[0]);
				studentCourseFullInfo.setStudent_document((String) result[1]);
				studentCourseFullInfo.setCourse_name((String) result[2]);
				studentCourseFullInfo.setCourse_field((String) result[3]);
				studentCourseFullInfo.setCourse_price((BigDecimal) result[4]);
				studentCourseFullInfo.setCourse_modality((String) result[5]);
				studentCourseFullInfo.setClassroom_period((String) result[6]);
				studentCourseFullInfo.setClassroom_start_time((String) result[7]);
				studentCourseFullInfo.setClassroom_end_time((String) result[8]);
				studentCourseFullInfo.setClassroom_start_date((String) result[9]);
				studentCourseFullInfo.setClassroom_end_date((String) result[10]);
				studentCourseFullInfo.setTeacher_name((String) result[11]);
				studentCourseFullInfoRes.add(studentCourseFullInfo);

			}
			
			customResp = emailSenderFeingClients.sendfullInfoCourseEmail(studentCourseFullInfoRes.get(0)).getBody();

			if (customResp.getStatusCode() == 200) {
				return new ResponseEntity<>(customResp, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(customResp, HttpStatus.INTERNAL_SERVER_ERROR);
			}


		} catch (Exception e) {
			CustomResponse ExceptionError = new CustomResponse();
			ExceptionError.setMessage(e.toString());
			ExceptionError.setStatusCode(500);
			ExceptionError.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionError, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}