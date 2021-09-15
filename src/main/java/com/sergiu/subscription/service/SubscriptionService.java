package com.sergiu.subscription.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sergiu.subscription.dto.RequestSubscriptionDTO;
import com.sergiu.subscription.dto.ReturnSubscriptionDTO;
import com.sergiu.subscription.entities.Plan;
import com.sergiu.subscription.entities.Product;
import com.sergiu.subscription.entities.Subscription;
import com.sergiu.subscription.entities.User;
import com.sergiu.subscription.entities.UserRepository;
import com.sergiu.subscription.exceptions.DuplicateSubscription;
import com.sergiu.subscription.mappers.SubscriptionMapper;
import com.sergiu.subscription.repository.PlanRepository;
import com.sergiu.subscription.repository.ProductRepository;
import com.sergiu.subscription.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	private PlanRepository planRepo;

	private ProductRepository productRepo;

	private SubscriptionRepository subscriptionRepo;

	private UserRepository userRepo;

	private SubscriptionMapper subMapper;

	public SubscriptionService(PlanRepository planRepo, ProductRepository productRepo,
			SubscriptionRepository subscriptionRepo, UserRepository userRepo, SubscriptionMapper subMapper) {
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
				throw new DuplicateSubscription("User already has a subscription. Delete it to create a new one.");
			}
		} else {
			// throw user not available execption
		}
		Optional<Plan> planOptional = planRepo.findById(subscriptionDto.getPlanId());
		if (planOptional.isPresent()) {
			subscription.setPlan(planOptional.get());
		} else {
			// throw option not available error
		}
		List<Product> products = productRepo.findAllById(subscriptionDto.getProducts());
		if (!products.isEmpty()) {
			subscription.getProducts().addAll(products);
		} else {
			// throw product not available
		}
		return subMapper.toDto(subscriptionRepo.save(subscription));
	}

	// as a mean of protection the logged in user should be able to delete only
	// their own subscription
	public void deleteSubscription(long userId) {
		try {
			subscriptionRepo.deleteById(userId);// sub and user id should be the same
		} catch (Exception e) {
			// throw cannot delete exception
		}
	}

	public ResponseEntity<Object> getSubscriptionByUser(long userId) {
		Optional<Subscription> subOptional = subscriptionRepo.findById(userId);
		if (subOptional.isPresent()) {
			return new ResponseEntity<>(subMapper.toDto(subOptional.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
