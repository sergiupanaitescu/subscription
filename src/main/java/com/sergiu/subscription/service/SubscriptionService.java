package com.sergiu.subscription.service;

import com.sergiu.subscription.dto.RequestSubscriptionDTO;
import com.sergiu.subscription.dto.ReturnSubscriptionDTO;

public interface SubscriptionService {

	public ReturnSubscriptionDTO addSubscription(RequestSubscriptionDTO subscriptionDto);

	public void deleteSubscription(long userId);

	// i smell N+1
	public ReturnSubscriptionDTO getSubscriptionByUser(long userId);

	public ReturnSubscriptionDTO updateSubscription(RequestSubscriptionDTO updatedSubscription);

}
