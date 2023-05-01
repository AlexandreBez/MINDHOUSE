package com.studentsystem.customqueryresult;

import java.math.BigDecimal;

public class StudentCourseInfo {

	private Integer payment_id;
	private Integer grade_id;
	private Integer student_class_course_id;
	private String student_name;
	private String student_document;
	private String student_email;
	private String teacher_name;
	private Integer course_id;
	private String course_name;
	private String course_field;
	private BigDecimal course_price;
	private String course_modality;
	private String classroom_period;
	private String classroom_start_date;
	private String classroom_end_date;
	private String classroom_start_time;
	private String classroom_end_time;

	public StudentCourseInfo() {
	}

	// All data constructor
	public StudentCourseInfo(Integer payment_id, Integer grade_id, Integer student_class_course_id, Integer course_id,
			String course_name, String course_field, String course_modality, String classroom_start_date,
			String classroom_end_date) {
		super();
		this.payment_id = payment_id;
		this.grade_id = grade_id;
		this.student_class_course_id = student_class_course_id;
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_field = course_field;
		this.course_modality = course_modality;
		this.classroom_start_date = classroom_start_date;
		this.classroom_end_date = classroom_end_date;
	}

	// Small info
	public StudentCourseInfo(Integer payment_id, Integer grade_id, Integer student_class_course_id, String student_name,
			String student_document, String student_email, String teacher_name, Integer course_id, String course_name,
			String course_field, BigDecimal course_price, String course_modality, String classroom_period,
			String classroom_start_date, String classroom_end_date, String classroom_start_time,
			String classroom_end_time) {
		super();
		this.student_class_course_id = student_class_course_id;
		this.student_name = student_name;
		this.student_document = student_document;
		this.student_email = student_email;
		this.teacher_name = teacher_name;
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_field = course_field;
		this.course_price = course_price;
		this.course_modality = course_modality;
		this.classroom_period = classroom_period;
		this.classroom_start_date = classroom_start_date;
		this.classroom_end_date = classroom_end_date;
		this.classroom_start_time = classroom_start_time;
		this.classroom_end_time = classroom_end_time;
	}

	// for delete the course
	public StudentCourseInfo(String student_name,String student_document,String student_email,String course_name,BigDecimal course_price) {
		super();
		this.student_name = student_name;
		this.student_document = student_document;
		this.student_email = student_email;
		this.course_name = course_name;
		this.course_price = course_price;
	}
	
	// for get all course info or for send the email with infos
	public StudentCourseInfo(String student_name,String student_document, String student_email,String course_name,String course_field, BigDecimal course_price, String course_modality,
			String classroom_start_date,String classroom_end_date,String classroom_start_time,String classroom_end_time,String teacher_name
	) {
		super();
		this.student_email = student_email;
		this.student_document = student_document;
		this.course_name = course_name;
		this.course_field = course_field;
		this.course_price = course_price;
		this.course_modality = course_modality;
		this.classroom_start_date = classroom_start_date;
		this.classroom_end_date = classroom_end_date;
		this.classroom_start_time = classroom_start_time;
		this.classroom_end_time = classroom_end_time;
		this.teacher_name = teacher_name;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public Integer getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}

	public Integer getStudent_class_course_id() {
		return student_class_course_id;
	}

	public void setStudent_class_course_id(Integer student_class_course_id) {
		this.student_class_course_id = student_class_course_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_document() {
		return student_document;
	}

	public void setStudent_document(String student_document) {
		this.student_document = student_document;
	}

	public String getStudent_email() {
		return student_email;
	}

	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_field() {
		return course_field;
	}

	public void setCourse_field(String course_field) {
		this.course_field = course_field;
	}

	public BigDecimal getCourse_price() {
		return course_price;
	}

	public void setCourse_price(BigDecimal course_price) {
		this.course_price = course_price;
	}

	public String getCourse_modality() {
		return course_modality;
	}

	public void setCourse_modality(String course_modality) {
		this.course_modality = course_modality;
	}

	public String getClassroom_period() {
		return classroom_period;
	}

	public void setClassroom_period(String classroom_period) {
		this.classroom_period = classroom_period;
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

	public String getClassroom_start_time() {
		return classroom_start_time;
	}

	public void setClassroom_start_time(String classroom_start_time) {
		this.classroom_start_time = classroom_start_time;
	}

	public String getClassroom_end_time() {
		return classroom_end_time;
	}

	public void setClassroom_end_time(String classroom_end_time) {
		this.classroom_end_time = classroom_end_time;
	}

}
