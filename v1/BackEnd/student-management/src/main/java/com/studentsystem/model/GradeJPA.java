package com.studentsystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.studentsystem.model.entity.Grade;

public interface GradeJPA extends JpaRepository<Grade, Integer> {

	@Query(value = "SELECT e.grade_id, o.course_name, t.teacher_name, e.first_note, e.second_note, e.third_note, e.additional_note, e.final_note, e.status, c.classroom_start_date, c.classroom_end_date\r\n"
			+ "FROM Grade e\r\n"
			+ "INNER JOIN Classroom c ON e.fk_classroom = c.classroom_id\r\n"
			+ "INNER JOIN Teacher t ON c.fk_teacher = t.teacher_id\r\n"
			+ "INNER JOIN Course o ON c.fk_course = o.course_id\r\n"
			+ "WHERE e.fk_student = :id",
		nativeQuery = true
	)
	List<Object[]> getStudentGradesByStudentId(@Param("id") Integer id);
	
	@Query(value = "SELECT e.grade_id, o.course_name, t.teacher_name, e.first_note, e.second_note, e.third_note, e.additional_note, e.final_note, e.status, c.classroom_start_date, c.classroom_end_date\r\n"
			+ "FROM Grade e\r\n"
			+ "INNER JOIN Classroom c ON e.fk_classroom = c.classroom_id\r\n"
			+ "INNER JOIN Teacher t ON c.fk_teacher = t.teacher_id\r\n"
			+ "INNER JOIN Course o ON c.fk_course = o.course_id\r\n"
			+ "WHERE o.course_name like %:name% AND e.fk_student = :id",
		nativeQuery = true
	)
	List<Object[]> getStudentGradesByStudentIdAndCourseName(@Param("id") Integer id, @Param("name") String name);
	
	@Query(
		value = "SELECT \r\n"
				+ "    s.student_email, \r\n"
				+ "    g.grade_id, \r\n"
				+ "    o.course_name, \r\n"
				+ "    t.teacher_name, \r\n"
				+ "    g.first_note, \r\n"
				+ "    g.second_note, \r\n"
				+ "    g.third_note, \r\n"
				+ "    g.additional_note, \r\n"
				+ "    g.final_note, \r\n"
				+ "    g.status, \r\n"
				+ "    c.classroom_start_date, \r\n"
				+ "    c.classroom_end_date\r\n"
				+ "FROM GRADE g\r\n"
				+ "INNER JOIN CLASSROOM c ON g.FK_CLASSROOM = c.CLASSROOM_ID\r\n"
				+ "INNER JOIN STUDENT s ON g.FK_STUDENT = s.STUDENT_ID\r\n"
				+ "INNER JOIN TEACHER t ON c.FK_TEACHER = t.TEACHER_ID\r\n"
				+ "INNER JOIN COURSE o ON c.FK_COURSE = o.COURSE_ID\r\n"
				+ "WHERE g.grade_id = :id",
		nativeQuery = true
	)
	List<Object[]> getStudentGradesByStudentIdAndSendEmailWithInfo(@Param("id") Integer id);
}
