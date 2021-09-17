package com.sergiu.subscription.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sergiu.subscription.dto.SubscriptionOptionsDTO;
import com.sergiu.subscription.entities.Plan;
import com.sergiu.subscription.entities.Product;
import com.sergiu.subscription.mappers.PlanMapper;
import com.sergiu.subscription.mappers.ProductMapper;
import com.sergiu.subscription.repository.PlanRepository;
import com.sergiu.subscription.repository.ProductRepository;
import com.sergiu.subscription.service.SubscriptionOptionsService;

@Service
public class SubscriptionOptionsServiceImpl implements SubscriptionOptionsService{

	private PlanRepository planRepo;

	private ProductRepository productRepo;

	public SubscriptionOptionsServiceImpl(PlanRepository planRepo, ProductRepository productRepo) {
		this.planRepo = planRepo;
		this.productRepo = productRepo;
	}

	public SubscriptionOptionsDTO getSubscriptionOptions() {
		List<Plan> plans = planRepo.findAll();
		List<Product> products = productRepo.findAll();
		SubscriptionOptionsDTO options = new SubscriptionOptionsDTO();
		options.setPlans(plans.stream().map(new PlanMapper()::toDto).collect(Collectors.toList()));
		options.setProducts(products.stream().map(new ProductMapper()::toDto).collect(Collectors.toList()));
		return options;
	}

}
