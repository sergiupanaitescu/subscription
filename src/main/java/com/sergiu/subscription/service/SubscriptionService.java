package com.sergiu.subscription.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sergiu.subscription.dto.RequestSubscriptionDTO;
import com.sergiu.subscription.dto.ReturnSubscriptionDTO;
import com.sergiu.subscription.entities.Plan;
import com.sergiu.subscription.entities.Product;
import com.sergiu.subscription.entities.Subscription;
import com.sergiu.subscription.entities.User;
import com.sergiu.subscription.entities.UserRepository;
import com.sergiu.subscription.exceptions.DuplicateSubscriptionException;
import com.sergiu.subscription.exceptions.GenericRuntimeMessageException;
import com.sergiu.subscription.exceptions.ObjectNotFoundException;
import com.sergiu.subscription.mappers.ReturnSubscriptionMapper;
import com.sergiu.subscription.repository.PlanRepository;
import com.sergiu.subscription.repository.ProductRepository;
import com.sergiu.subscription.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);


	private PlanRepository planRepo;

	private ProductRepository productRepo;

	private SubscriptionRepository subscriptionRepo;

	private UserRepository userRepo;

	private ReturnSubscriptionMapper subMapper;

	public SubscriptionService(PlanRepository planRepo, ProductRepository productRepo,
			SubscriptionRepository subscriptionRepo, UserRepository userRepo, ReturnSubscriptionMapper subMapper) {
		this.planRepo = planRepo;
		this.productRepo = productRepo;
		this.subscriptionRepo = subscriptionRepo;
		this.userRepo = userRepo;
		this.subMapper = subMapper;
	}

	public ReturnSubscriptionDTO addSubscription(RequestSubscriptionDTO subscriptionDto) {
		Subscription subscription = new Subscription();
		Optional<User> userOptional = userRepo.findById(subscriptionDto.getUserId());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			long usersSubscriptions = subscriptionRepo.countByUserId(user.getId());
			if (usersSubscriptions == 0) {
				subscription.setUser(user);
			} else {
				logger.debug(String.format("Duplicate subsciption try for user %s", user.getId()));
				throw new DuplicateSubscriptionException(
						"User already has a subscription. Delete it to create a new one.");//TODO move to constants
			}
		} else {
			throw new GenericRuntimeMessageException("User does not exist!");
		}
		return saveOrUpdateSubscription(subscriptionDto, subscription);

	}

	// as a mean of protection the logged in user should be able to delete only
	// their own subscription
	public void deleteSubscription(long userId) {
		try {
			subscriptionRepo.deleteById(userId);// sub and user id should be the same
		} catch (Exception e) {
			logger.error(String.format("Could not delte subscription with id: %s", userId));
			throw new GenericRuntimeMessageException("Could not delete subscription!");
		}
	}

	public ReturnSubscriptionDTO getSubscriptionByUser(long userId) {
		Optional<Subscription> subOptional = subscriptionRepo.findById(userId);
		if (subOptional.isPresent()) {
			return subMapper.toDto(subOptional.get());
		} else {
			throw new ObjectNotFoundException("Subscription does not exist!");
		}
	}

	public ReturnSubscriptionDTO updateSubscription(RequestSubscriptionDTO updatedSubscription, long userId) {
		Optional<Subscription> subscriptionOptional = subscriptionRepo.findById(userId);
		if (subscriptionOptional.isPresent()) {
			Subscription sub = subscriptionOptional.get();
			return saveOrUpdateSubscription(updatedSubscription, sub);
		} else {
	        logger.error(String.format("Subscription with id: %s not found!", userId));
			throw new ObjectNotFoundException("Selected subscription does not exist anymore!");
		}

	}

	private ReturnSubscriptionDTO saveOrUpdateSubscription(RequestSubscriptionDTO subscriptionDto,
			Subscription subscription) {
		Optional<Plan> planOptional = planRepo.findById(subscriptionDto.getPlanId());
		if (planOptional.isPresent()) {
			subscription.setPlan(planOptional.get());
		} else {
			throw new ObjectNotFoundException("Selected plan is not available!");
		}
		List<Product> products = productRepo.findAllById(subscriptionDto.getProducts());
		if (!products.isEmpty()) {
			subscription.getProducts().clear();
			subscription.getProducts().addAll(products);
		} else {
			throw new ObjectNotFoundException("Some selected products are not available!");
		}
		return subMapper.toDto(subscriptionRepo.save(subscription));
	}
}
