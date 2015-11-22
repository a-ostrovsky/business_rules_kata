package com.kata.businessrules;

public class RuleEngineCreationException extends Exception {
	
	private static final long serialVersionUID = -5338760833138789731L;
	
	public RuleEngineCreationException(String message) {
		super(message);
	}

	public RuleEngineCreationException(String message,
			Throwable throwable) {
		super(message, throwable);
	}
}
