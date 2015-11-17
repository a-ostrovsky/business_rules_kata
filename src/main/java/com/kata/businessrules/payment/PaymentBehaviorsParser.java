package com.kata.businessrules.payment;

import java.util.Collection;

public interface PaymentBehaviorsParser<T> {
	Collection<PaymentBehavior> parse(T behaviorDefinition);
}
