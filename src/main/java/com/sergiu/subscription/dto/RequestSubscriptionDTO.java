package com.sergiu.subscription.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class RequestSubscriptionDTO {

	@NotNull
	private Long userId;

	@NotEmpty
	private List<Long> products = new ArrayList<>();

	@NotNull
	private Long planId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getProducts() {
		return products;
	}

	public void setProducts(List<Long> products) {
		this.products = products;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

}
