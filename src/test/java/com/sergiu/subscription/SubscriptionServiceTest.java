package com.sergiu.subscription;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.Matchers;

import com.sergiu.subscription.constants.PlanType;
import com.sergiu.subscription.dto.RequestSubscriptionDTO;
import com.sergiu.subscription.entities.Plan;
import com.sergiu.subscription.entities.Product;
import com.sergiu.subscription.entities.Subscription;
import com.sergiu.subscription.entities.User;
import com.sergiu.subscription.entities.UserRepository;
import com.sergiu.subscription.exceptions.DuplicateSubscriptionException;
import com.sergiu.subscription.exceptions.GenericRuntimeMessageException;
import com.sergiu.subscription.mappers.PlanMapper;
import com.sergiu.subscription.mappers.ProductMapper;
import com.sergiu.subscription.mappers.ReturnSubscriptionMapper;
import com.sergiu.subscription.repository.PlanRepository;
import com.sergiu.subscription.repository.ProductRepository;
import com.sergiu.subscription.repository.SubscriptionRepository;
import com.sergiu.subscription.service.impl.SubscriptionServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SubscriptionServiceTest {

	private PlanRepository planRepo = Mockito.mock(PlanRepository.class);

	private ProductRepository productRepo = Mockito.mock(ProductRepository.class);

	private SubscriptionRepository subscriptionRepo = Mockito.mock(SubscriptionRepository.class);

	private UserRepository userRepo = Mockito.mock(UserRepository.class);

	private SubscriptionServiceImpl subservice;

	private User user;

	private Product product;

	private Plan plan;

	private Subscription subscription;

	@BeforeEach
	public void prepare() {
		subservice = new SubscriptionServiceImpl(planRepo, productRepo, subscriptionRepo, userRepo,
				new ReturnSubscriptionMapper(new PlanMapper(), new ProductMapper()));
		user = new User();
		user.setAddress("address");
		user.setEmail("some@email.com");
		user.setId(1L);
		user.setName("Name");
		user.setUsername("Username");
		product = new Product();
		product.setId(1L);
		product.setPrice(50);
		product.setName("Prod1");
		plan = new Plan();
		plan.setId(1L);
		plan.setPlanType(PlanType.MONTHLY);
		plan.setDiscount(50);
		subscription = new Subscription();
		subscription.setId(1L);
		subscription.setPlan(plan);
		subscription.getProducts().add(product);
		subscription.setUser(user);
	}

	@Test
	public void testCreateSubscriptionOk() {
		RequestSubscriptionDTO dto = new RequestSubscriptionDTO();
		dto.setPlanId(1L);
		dto.getProducts().add(1L);
		dto.setUserId(1L);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.ofNullable(user));
		Mockito.when(productRepo.findAllById(Arrays.asList(1L))).thenReturn(Arrays.asList(product));
		Mockito.when(planRepo.findById(1L)).thenReturn(Optional.ofNullable(plan));
		Mockito.when(subscriptionRepo.save(Matchers.any(Subscription.class))).thenReturn(subscription);
		assertNotNull(subservice.addSubscription(dto));
	}
	
	@Test
	public void testCreateSubscriptionFailWrongUser() {
		RequestSubscriptionDTO dto = new RequestSubscriptionDTO();
		dto.setPlanId(1L);
		dto.getProducts().add(1L);
		dto.setUserId(1L);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.ofNullable(null));
		Mockito.when(productRepo.findAllById(Arrays.asList(1L))).thenReturn(Arrays.asList(product));
		Mockito.when(planRepo.findById(1L)).thenReturn(Optional.ofNullable(plan));
		Mockito.when(subscriptionRepo.save(Matchers.any(Subscription.class))).thenReturn(subscription);
		GenericRuntimeMessageException exception = assertThrows(GenericRuntimeMessageException.class, () -> {
			subservice.addSubscription(dto);
		});
		assertTrue(exception.getMessage().contains("User does not exist!"));
	}
	
	@Test
	public void testCreateSubscriptionFailAlreadyExist() {
		RequestSubscriptionDTO dto = new RequestSubscriptionDTO();
		dto.setUserId(1L);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.ofNullable(user));
		Mockito.when(subscriptionRepo.countByUserId(1L)).thenReturn(1L);
		DuplicateSubscriptionException exception = assertThrows(DuplicateSubscriptionException.class, () -> {
			subservice.addSubscription(dto);
		});
		assertTrue(exception.getMessage().contains("User already has a subscription"));
		
	}

}
