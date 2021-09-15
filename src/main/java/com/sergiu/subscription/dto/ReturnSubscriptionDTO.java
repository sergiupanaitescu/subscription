package com.sergiu.subscription.dto;

import java.util.ArrayList;
import java.util.List;

public class ReturnSubscriptionDTO {

	private Long subscriptionId;

	private PlanDTO plan;

	private List<ProductDTO> products = new ArrayList<>();

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public PlanDTO getPlan() {
		return plan;
	}

	public void setPlan(PlanDTO plan) {
		this.plan = plan;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
