package com.sergiu.subscription.mappers;

import org.springframework.stereotype.Component;

import com.sergiu.subscription.dto.ProductDTO;
import com.sergiu.subscription.entities.Product;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

	@Override
	public Product toEntity(ProductDTO dto) {
		Product prod = new Product();
		prod.setDescription(dto.getDescription());
		prod.setName(dto.getName());
		prod.setPrice(dto.getPrice());
		return prod;
	}

	@Override
	public ProductDTO toDto(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		return dto;
	}

}
