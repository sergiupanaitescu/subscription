package com.sergiu.subscription.mappers;

public interface Mapper<E,D> {

	public E toEntity(D dto);
	
	public D toDto(E entity);
}
