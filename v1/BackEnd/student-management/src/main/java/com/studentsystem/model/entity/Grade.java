package com.studentsystem.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GRADE")
public class Grade {

	@Id
	@Column(name = "GRADE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer grade_id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_classroom", nullable = false)
	private Classroom fk_classroom;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_student", nullable = false)
	private Student fk_student;
	
	@Column(nullable = true, name = "FIRST_NOTE")
	private BigDecimal first_note;
	
	@Column(nullable = true, name = "SECOND_NOTE")
	private BigDecimal second_note;
	
	@Column(nullable = true, name = "THIRD_NOTE")
	private BigDecimal third_note;
	
	@Column(nullable = true, name = "ADDITIONAL_NOTE")
	private BigDecimal additional_note;
	
	@Column(nullable = true, name = "FINAL_NOTE")
	private BigDecimal final_note;
	
	@Column(nullable = false, name = "STATUS", length = 15)
	private String status;
	
	@Column(nullable = false, name = "GRADE_CREATION_DATE", length = 10)
	private String grade_creation_date;

	public Grade() {}
	
	public Grade(Classroom fk_classroom, Student fk_student, BigDecimal first_note, BigDecimal second_note,
			BigDecimal third_note, BigDecimal additional_note, BigDecimal final_note, String status,
			String grade_creation_date) {
		super();
		this.fk_classroom = fk_classroom;
		this.fk_student = fk_student;
		this.first_note = first_note;
		this.second_note = second_note;
		this.third_note = third_note;
		this.additional_note = additional_note;
		this.final_note = final_note;
		this.status = status;
		this.grade_creation_date = grade_creation_date;
	}

	public Integer getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}

	public Classroom getFk_classroom() {
		return fk_classroom;
	}

	public void setFk_class(Classroom fk_classroom) {
		this.fk_classroom = fk_classroom;
	}

	public Student getFk_student() {
		return fk_student;
	}

	public void setFk_student(Student fk_student) {
		this.fk_student = fk_student;
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

	public String getGrade_creation_date() {
		return grade_creation_date;
	}

	public void setGrade_creation_date(String grade_creation_date) {
		this.grade_creation_date = grade_creation_date;
	}
	
}
