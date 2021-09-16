package com.sergiu.subscription.exceptions;

public class DuplicateSubscriptionException extends RuntimeException {

	public DuplicateSubscriptionException() {
	}

	public DuplicateSubscriptionException(String message) {
		super(message);
	}

}
