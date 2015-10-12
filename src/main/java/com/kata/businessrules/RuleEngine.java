package com.kata.businessrules;

import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.products.Product;

public class RuleEngine {

	private final Logger logger = LoggerFactory.getLogger(RuleEngine.class);
	private Iterable<PaymentBehavior> paymentBehaviors;

	public RuleEngine(Iterable<PaymentBehavior> paymentBehaviors) {
		Preconditions.checkNotNull(paymentBehaviors);
		this.paymentBehaviors = paymentBehaviors;
	}

	public void pay(CurrentUsers users, Product product) {
		logger.debug("Customer paid");
		users.getCustomer().purchase(product);
		StreamSupport.stream(paymentBehaviors.spliterator(), false)
				.filter(processor -> processor.canProcess(product))
				.forEach(processor -> processor.pay(users, product));
	}
}
