package com.sergiu.subscription.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.subscription.dto.RequestSubscriptionDTO;
import com.sergiu.subscription.dto.ReturnSubscriptionDTO;
import com.sergiu.subscription.service.SubscriptionService;

@RestController
@RequestMapping(path = "/api/v1/subscriptions")
public class SubscriptionController {

	private SubscriptionService subService;

	public SubscriptionController(SubscriptionService subService) {
		this.subService = subService;
	}

	@PostMapping
	public ReturnSubscriptionDTO createSubscription(@Validated @RequestBody RequestSubscriptionDTO subscription) {
		return subService.addSubscription(subscription);
	}

	@DeleteMapping
	@RequestMapping(path = "/delete/user/{userId}")
	public void deleteSubscription(@PathVariable("userId") Long userId) {
		subService.deleteSubscription(userId);
	}

	@GetMapping
	@RequestMapping(path = "/user/{userId}")
	public ReturnSubscriptionDTO getUserSubecription(@PathVariable("userId") Long userId) {
		return subService.getSubscriptionByUser(userId);
	}

	@PutMapping
	public ReturnSubscriptionDTO updateSubscription(@Validated @RequestBody RequestSubscriptionDTO subscription) {
		return subService.updateSubscription(subscription);
	}
}
