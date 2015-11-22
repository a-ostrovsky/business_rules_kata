package com.kata.businessrules;

public class RuleEngineCreationException extends Exception {	
	
	private static final long serialVersionUID = -4820884111905552888L;

	public RuleEngineCreationException() {
		super(Messages.get("couldNotCreateRuleEngine"));
	}
	
	public RuleEngineCreationException(Throwable throwable) {
		super(Messages.get("couldNotCreateRuleEngine"), throwable);
	}
}
