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
@Table(name = "PAYMENT")
public class Payment {

	@Id
	@Column(name = "PAYMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer payment_id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_student", nullable = false)
	private Student fk_student;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_course", nullable = false)
	private Course fk_course;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "fk_promotion", nullable = true)
	private Promotion fk_promotion;
	
	@Column(nullable = false, name = "PAYMENT_TOTAL_AMOUNT")
	private BigDecimal payment_total_amount;
	
	@Column(nullable = false, name = "PAYMENT_METHOD")
	private String payment_method;
	
	@Column(nullable = false, name = "PAYMENT_CREATION_DATE", length = 10)
	private String payment_creation_date;

	public Payment() {}

	public Payment(Student fk_student, Course fk_course, Promotion fk_promotion,
			BigDecimal payment_total_amount, String payment_method, String payment_creation_date) {
		super();
		this.fk_student = fk_student;
		this.fk_course = fk_course;
		this.fk_promotion = fk_promotion;
		this.payment_total_amount = payment_total_amount;
		this.payment_method = payment_method;
		this.payment_creation_date = payment_creation_date;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public Student getFk_student() {
		return fk_student;
	}

	public void setFk_student(Student fk_student) {
		this.fk_student = fk_student;
	}

	public Course getFk_course() {
		return fk_course;
	}

	public void setFk_course(Course fk_course) {
		this.fk_course = fk_course;
	}

	public Promotion getFk_promotion() {
		return fk_promotion;
	}

	public void setFk_promotion(Promotion fk_promotion) {
		this.fk_promotion = fk_promotion;
	}

	public BigDecimal getPayment_total_amount() {
		return payment_total_amount;
	}

	public void setPayment_total_amount(BigDecimal payment_total_amount) {
		this.payment_total_amount = payment_total_amount;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getPayment_creation_date() {
		return payment_creation_date;
	}

	public void setPayment_creation_date(String payment_creation_date) {
		this.payment_creation_date = payment_creation_date;
	}
	
}
