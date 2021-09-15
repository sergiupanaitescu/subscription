package com.sergiu.subscription.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sergiu.subscription.constants.PlanType;

@Entity
@Table(name = "plans")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private PlanType plan_type;

	private Integer discount;

	private String name;

	public Plan() {

	}

	public Plan(Long id, PlanType planType, Integer discount, String name) {
		this.id = id;
		this.plan_type = planType;
		this.discount = discount;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlanType getPlanType() {
		return plan_type;
	}

	public void setPlanType(PlanType planType) {
		this.plan_type = planType;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
