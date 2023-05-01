package com.studentsystem.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_CLASSROOM_COURSE")
public class Student_Classroom_Course {

	@Id
	@Column(name = "STUDENT_CLASSROOM_COURSE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer student_classroom_course_id;
	
	@Column(name = "FK_STUDENT")
	private Integer fk_student;
	
	@Column(name = "FK_CLASSROOM")
	private Integer fk_classroom;
	
	@Column(name = "FK_COURSE")
	private Integer fk_course;
	
	public Student_Classroom_Course() {}

	public Student_Classroom_Course(Integer student_classroom_course_id, Integer fk_student, Integer fk_classroom,
			Integer fk_course) {
		super();
		this.student_classroom_course_id = student_classroom_course_id;
		this.fk_student = fk_student;
		this.fk_classroom = fk_classroom;
		this.fk_course = fk_course;
	}

	public Integer getStudent_classroom_course_id() {
		return student_classroom_course_id;
	}

	public void setStudent_classroom_course_id(Integer student_classroom_course_id) {
		this.student_classroom_course_id = student_classroom_course_id;
	}

	public Integer getFk_student() {
		return fk_student;
	}

	public void setFk_student(Integer fk_student) {
		this.fk_student = fk_student;
	}

	public Integer getFk_classroom() {
		return fk_classroom;
	}

	public void setFk_classroom(Integer fk_classroom) {
		this.fk_classroom = fk_classroom;
	}

	public Integer getFk_course() {
		return fk_course;
	}

	public void setFk_course(Integer fk_course) {
		this.fk_course = fk_course;
	}
	
}
