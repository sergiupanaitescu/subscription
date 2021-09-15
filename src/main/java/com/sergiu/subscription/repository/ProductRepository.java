package com.sergiu.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergiu.subscription.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
