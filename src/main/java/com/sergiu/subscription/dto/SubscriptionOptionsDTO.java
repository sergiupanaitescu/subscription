package com.sergiu.subscription.dto;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionOptionsDTO {

	private List<PlanDTO> plans = new ArrayList<>(0);

	private List<ProductDTO> products = new ArrayList<>();

	public List<PlanDTO> getPlans() {
		return plans;
	}

	public void setPlans(List<PlanDTO> plans) {
		this.plans = plans;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
