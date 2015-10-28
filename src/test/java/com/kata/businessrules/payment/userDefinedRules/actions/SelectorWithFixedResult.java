package com.kata.businessrules.payment.userDefinedRules.actions;

public class SelectorWithFixedResult<T> implements Selector<T> {

	private T fixedResult;
	private T expectedValue;

	public SelectorWithFixedResult(T expectedValue, T fixedResult) {
		this.expectedValue = expectedValue;
		this.fixedResult = fixedResult;
	}

	@Override
	public T select(T providedValue) {
		if (providedValue != null && providedValue.equals(expectedValue)) {
			return fixedResult;
		} else {
			return null;
		}
	}

}
