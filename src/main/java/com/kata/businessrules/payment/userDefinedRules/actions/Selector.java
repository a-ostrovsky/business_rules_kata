package com.kata.businessrules.payment.userDefinedRules.actions;

public interface Selector<T> {
	T select(T providedValue);
}
