package com.sergiu.subscription.mappers;

import org.springframework.stereotype.Component;

import com.sergiu.subscription.dto.PlanDTO;
import com.sergiu.subscription.entities.Plan;

@Component
public class PlanMapper implements Mapper<Plan, PlanDTO> {

	@Override
	public Plan toEntity(PlanDTO dto) {
		Plan plan = new Plan();
		plan.setDiscount(dto.getDiscount());
		plan.setName(dto.getName());
		plan.setPlanType(dto.getPlanType());
		return plan;
	}

	@Override
	public PlanDTO toDto(Plan entity) {
		PlanDTO dto = new PlanDTO();
		dto.setId(entity.getId());
		dto.setDiscount(entity.getDiscount());
		dto.setName(entity.getName());
		dto.setPlanType(entity.getPlanType());
		return dto;
	}

}
