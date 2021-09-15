package com.sergiu.subscription.exceptions;

public class DuplicateSubscription extends RuntimeException {

	public DuplicateSubscription() {
	}

	public DuplicateSubscription(String message) {
		super(message);
	}

}
