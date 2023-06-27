package com.studenthome.FileGenerator.objHelper;

import java.math.BigDecimal;

public class StudentPaymentsInfo {

	private Integer payment_id;
	private String course_name;
	private BigDecimal course_price; 
	private String promotion_name;
	private BigDecimal promotion_descount_amount;
	private BigDecimal payment_total_amount;
	private String payment_method;
	private String payment_status;
	private String payment_creation_date;
	private String student_email;
	
	public StudentPaymentsInfo() {}

	public StudentPaymentsInfo(Integer payment_id, String course_name, BigDecimal course_price, String promotion_name,
			BigDecimal promotion_descount_amount, BigDecimal payment_total_amount, String payment_method,
			String payment_status, String payment_creation_date) {
		super();
		this.payment_id = payment_id;
		this.course_name = course_name;
		this.course_price = course_price;
		this.promotion_name = promotion_name;
		this.promotion_descount_amount = promotion_descount_amount;
		this.payment_total_amount = payment_total_amount;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.payment_creation_date = payment_creation_date;
	}
	
	public StudentPaymentsInfo(Integer payment_id, String course_name, BigDecimal course_price, String promotion_name,
			BigDecimal promotion_descount_amount, BigDecimal payment_total_amount, String payment_method,
			String payment_status, String payment_creation_date, String student_email) {
		super();
		this.payment_id = payment_id;
		this.course_name = course_name;
		this.course_price = course_price;
		this.promotion_name = promotion_name;
		this.promotion_descount_amount = promotion_descount_amount;
		this.payment_total_amount = payment_total_amount;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.payment_creation_date = payment_creation_date;
		this.student_email = student_email;
	}

	public String getStudent_email() {
		return student_email;
	}

	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public BigDecimal getCourse_price() {
		return course_price;
	}

	public void setCourse_price(BigDecimal course_price) {
		this.course_price = course_price;
	}

	public String getPromotion_name() {
		return promotion_name;
	}

	public void setPromotion_name(String promotion_name) {
		this.promotion_name = promotion_name;
	}

	public BigDecimal getPromotion_descount_amount() {
		return promotion_descount_amount;
	}

	public void setPromotion_descount_amount(BigDecimal promotion_descount_amount) {
		this.promotion_descount_amount = promotion_descount_amount;
	}

	public BigDecimal getPayment_total_amount() {
		return payment_total_amount;
	}

	public void setPayment_total_amount(BigDecimal payment_total_amount) {
		this.payment_total_amount = payment_total_amount;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getPayment_creation_date() {
		return payment_creation_date;
	}

	public void setPayment_creation_date(String payment_creation_date) {
		this.payment_creation_date = payment_creation_date;
	}
	
}
