package com.sergiu.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergiu.subscription.entities.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
