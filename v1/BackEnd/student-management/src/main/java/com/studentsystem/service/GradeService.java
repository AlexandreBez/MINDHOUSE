package com.studentsystem.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studentsystem.customqueryresult.StudentGradesInfo;
import com.studentsystem.feingClients.EmailSenderFeingClients;
import com.studentsystem.model.GradeJPA;
import com.studentsystem.objects.CustomResponse;

import javax.transaction.Transactional;

@Service
public class GradeService {
	
	@Autowired
	EmailSenderFeingClients emailSenderFeingClients;

	@Autowired
	GradeJPA gradeRepository;

	@Transactional
	public ResponseEntity<List<StudentGradesInfo>> getStudentGradesByStudentId(Integer id) {
		try {

			List<Object[]> results = gradeRepository.getStudentGradesByStudentId(id);

			if (results.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			List<StudentGradesInfo> getStudentGradesInfoRes = new ArrayList<>();

			for (Object[] result : results) {

				StudentGradesInfo studentGradesInfo = new StudentGradesInfo();

				studentGradesInfo.setGrade_id((Integer) result[0]);
				studentGradesInfo.setCourse_name((String) result[1]);
				studentGradesInfo.setTeacher_name((String) result[2]);
				studentGradesInfo.setFirst_note((BigDecimal) result[3]);
				studentGradesInfo.setSecond_note((BigDecimal) result[4]);
				studentGradesInfo.setThird_note((BigDecimal) result[5]);
				studentGradesInfo.setAdditional_note((BigDecimal) result[6]);
				studentGradesInfo.setFinal_note((BigDecimal) result[7]);
				studentGradesInfo.setStatus((String) result[8]);
				studentGradesInfo.setClassroom_start_date((String) result[9]);
				studentGradesInfo.setClassroom_end_date((String) result[10]);

				getStudentGradesInfoRes.add(studentGradesInfo);

			}
			
			return new ResponseEntity<>(getStudentGradesInfoRes, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<List<StudentGradesInfo>> getStudentGradesByStudentIdAndCourseName(Integer id, String name) {
		try {

			List<Object[]> results = gradeRepository.getStudentGradesByStudentIdAndCourseName(id, name);

			if (results.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			List<StudentGradesInfo> getStudentGradesInfoRes = new ArrayList<>();

			for (Object[] result : results) {

				StudentGradesInfo studentGradesInfo = new StudentGradesInfo();

				studentGradesInfo.setGrade_id((Integer) result[0]);
				studentGradesInfo.setCourse_name((String) result[1]);
				studentGradesInfo.setTeacher_name((String) result[2]);
				studentGradesInfo.setFirst_note((BigDecimal) result[3]);
				studentGradesInfo.setSecond_note((BigDecimal) result[4]);
				studentGradesInfo.setThird_note((BigDecimal) result[5]);
				studentGradesInfo.setAdditional_note((BigDecimal) result[6]);
				studentGradesInfo.setFinal_note((BigDecimal) result[7]);
				studentGradesInfo.setStatus((String) result[8]);
				studentGradesInfo.setClassroom_start_date((String) result[9]);
				studentGradesInfo.setClassroom_end_date((String) result[10]);

				getStudentGradesInfoRes.add(studentGradesInfo);

			}
			
			return new ResponseEntity<>(getStudentGradesInfoRes, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<CustomResponse> getStudentGradesByStudentIdAndSendEmailWithInfo(Integer id) {
		
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {

			CustomResponse response = new CustomResponse();
			
			List<Object[]> results = gradeRepository.getStudentGradesByStudentIdAndSendEmailWithInfo(id);

			if (results.isEmpty()) {
				response.setMessage("Grade not found");
				response.setStatusCode(404);
				response.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			List<StudentGradesInfo> getStudentGradesInfoRes = new ArrayList<>();

			for (Object[] result : results) {

				StudentGradesInfo studentGradesInfo = new StudentGradesInfo();

				studentGradesInfo.setStudent_email((String) result[0]);
				studentGradesInfo.setGrade_id((Integer) result[1]);
				studentGradesInfo.setCourse_name((String) result[2]);
				studentGradesInfo.setTeacher_name((String) result[3]);
				studentGradesInfo.setFirst_note((BigDecimal) result[4]);
				studentGradesInfo.setSecond_note((BigDecimal) result[5]);
				studentGradesInfo.setThird_note((BigDecimal) result[6]);
				studentGradesInfo.setAdditional_note((BigDecimal) result[7]);
				studentGradesInfo.setFinal_note((BigDecimal) result[8]);
				studentGradesInfo.setStatus((String) result[9]);
				studentGradesInfo.setClassroom_start_date((String) result[10]);
				studentGradesInfo.setClassroom_end_date((String) result[11]);

				getStudentGradesInfoRes.add(studentGradesInfo);

			}
			
			response = emailSenderFeingClients.sendGradeInfoEmail(getStudentGradesInfoRes.get(0)).getBody();
			
			if (response.getStatusCode() == 200) {
				return new ResponseEntity<>(response, HttpStatus.OK); 
			} else {
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			CustomResponse Exceptionresponse = new CustomResponse();
			Exceptionresponse.setMessage(e.getMessage());
			Exceptionresponse.setStatusCode(500);
			Exceptionresponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(Exceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
