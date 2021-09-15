package com.sergiu.subscription.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.subscription.dto.SubscriptionOptionsDTO;
import com.sergiu.subscription.service.SubscriptionOptionsService;

@RestController
@RequestMapping(path = "/api/v1/subscriptions/options")
public class SubscriptionOptionsController {

	private SubscriptionOptionsService optionsService;

	public SubscriptionOptionsController(SubscriptionOptionsService optionsService) {
		this.optionsService = optionsService;
	}

	@GetMapping
	public SubscriptionOptionsDTO getSubscriptionOptions() {
		return optionsService.getSubscriptionOptions();
	}

}
