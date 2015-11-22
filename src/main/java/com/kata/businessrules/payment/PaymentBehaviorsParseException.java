package com.kata.businessrules.payment;

public class PaymentBehaviorsParseException extends Exception {

	private static final long serialVersionUID = 779810577183084005L;

	public PaymentBehaviorsParseException(String message) {
		super(message);
	}

	public PaymentBehaviorsParseException(String message,
			Throwable throwable) {
		super(message, throwable);
	}
}
