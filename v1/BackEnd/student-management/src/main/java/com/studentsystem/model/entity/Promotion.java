package com.studentsystem.model.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTION")
public class Promotion {
	
	@Id
	@Column(name = "PROMOTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer promotion_id;
	
	@Column(nullable = false, name = "PROMOTION_NAME", length = 100)
	private String promotion_name;
	
	@Column(nullable = false, name = "PROMOTION_DESCOUNT_AMOUNT")
	private BigDecimal promotion_descount_amount;
	
	@Column(nullable = false, name = "PROMOTION_DESCRIPTION", length = 250)
	private String promotion_description;
	
	@Column(nullable = false, name = "PROMOTION_CREATION_DATE", length = 10)
	private String promotion_creation_date;
	
	@OneToMany(mappedBy = "fk_promotion", orphanRemoval = false)
	private List<Payment> payment;

	public Promotion() {}

	public Promotion(String promotion_name, BigDecimal promotion_descount_amount, String promotion_description,
			String promotion_creation_date) {
		this.promotion_name = promotion_name;
		this.promotion_descount_amount = promotion_descount_amount;
		this.promotion_description = promotion_description;
		this.promotion_creation_date = promotion_creation_date;
	}

	public Integer getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
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

	public String getPromotion_description() {
		return promotion_description;
	}

	public void setPromotion_description(String promotion_description) {
		this.promotion_description = promotion_description;
	}

	public String getPromotion_creation_date() {
		return promotion_creation_date;
	}

	public void setPromotion_creation_date(String promotion_creation_date) {
		this.promotion_creation_date = promotion_creation_date;
	}
	
}
