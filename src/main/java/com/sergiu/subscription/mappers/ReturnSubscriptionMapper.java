package com.sergiu.subscription.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sergiu.subscription.dto.ReturnSubscriptionDTO;
import com.sergiu.subscription.entities.Subscription;

@Component
public class ReturnSubscriptionMapper implements Mapper<Subscription, ReturnSubscriptionDTO> {

	private PlanMapper planMapper;

	private ProductMapper productMapper;

	public ReturnSubscriptionMapper(PlanMapper planMapper, ProductMapper productMapper) {
		this.planMapper = planMapper;
		this.productMapper = productMapper;
	}

	@Override
	public Subscription toEntity(ReturnSubscriptionDTO dto) {
		// TODO
		return null;
	}

	@Override
	public ReturnSubscriptionDTO toDto(Subscription entity) {
		ReturnSubscriptionDTO dto = new ReturnSubscriptionDTO();
		dto.setPlan(planMapper.toDto(entity.getPlan()));
		dto.setProducts(entity.getProducts().stream().map(product -> {
			return productMapper.toDto(product);
		}).collect(Collectors.toList()));
		dto.setSubscriptionId(entity.getId());
		return dto;
	}

}
