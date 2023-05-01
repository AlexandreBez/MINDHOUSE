package com.studenthome.emailsender.objHelper;

import java.math.BigDecimal;

public class StudentGradesInfo {

	private Integer grade_id;
	private String student_email;
	private String course_name;
	private String teacher_name;
	private BigDecimal first_note;
	private BigDecimal second_note;
	private BigDecimal third_note;
	private BigDecimal additional_note;
	private BigDecimal final_note;
	private String status;
	private String classroom_start_date;
	private String classroom_end_date;
	
	public StudentGradesInfo() {}

	public StudentGradesInfo(String student_email, Integer grade_id, String course_name, String teacher_name,
			BigDecimal first_note, BigDecimal second_note, BigDecimal third_note, BigDecimal additional_note,
			BigDecimal final_note, String status, String classroom_start_date, String classroom_end_date) {
		super();
		this.student_email = student_email;
		this.grade_id = grade_id;
		this.course_name = course_name;
		this.teacher_name = teacher_name;
		this.first_note = first_note;
		this.second_note = second_note;
		this.third_note = third_note;
		this.additional_note = additional_note;
		this.final_note = final_note;
		this.status = status;
		this.classroom_start_date = classroom_start_date;
		this.classroom_end_date = classroom_end_date;
	}

	public StudentGradesInfo(Integer grade_id, String course_name, String teacher_name, BigDecimal first_note,
			BigDecimal second_note, BigDecimal third_note, BigDecimal additional_note, BigDecimal final_note,
			String status, String classroom_start_date, String classroom_end_date) {
		super();
		this.grade_id = grade_id;
		this.course_name = course_name;
		this.teacher_name = teacher_name;
		this.first_note = first_note;
		this.second_note = second_note;
		this.third_note = third_note;
		this.additional_note = additional_note;
		this.final_note = final_note;
		this.status = status;
		this.classroom_start_date = classroom_start_date;
		this.classroom_end_date = classroom_end_date;
	}
	
	public String getStudent_email() {
		return student_email;
	}

	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}

	public Integer getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public BigDecimal getFirst_note() {
		return first_note;
	}

	public void setFirst_note(BigDecimal first_note) {
		this.first_note = first_note;
	}

	public BigDecimal getSecond_note() {
		return second_note;
	}

	public void setSecond_note(BigDecimal second_note) {
		this.second_note = second_note;
	}

	public BigDecimal getThird_note() {
		return third_note;
	}

	public void setThird_note(BigDecimal third_note) {
		this.third_note = third_note;
	}

	public BigDecimal getAdditional_note() {
		return additional_note;
	}

	public void setAdditional_note(BigDecimal additional_note) {
		this.additional_note = additional_note;
	}

	public BigDecimal getFinal_note() {
		return final_note;
	}

	public void setFinal_note(BigDecimal final_note) {
		this.final_note = final_note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClassroom_start_date() {
		return classroom_start_date;
	}

	public void setClassroom_start_date(String classroom_start_date) {
		this.classroom_start_date = classroom_start_date;
	}

	public String getClassroom_end_date() {
		return classroom_end_date;
	}

	public void setClassroom_end_date(String classroom_end_date) {
		this.classroom_end_date = classroom_end_date;
	}
	
}
