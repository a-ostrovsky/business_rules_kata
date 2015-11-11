package com.kata.businessrules;

import java.util.Collection;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.products.Product;

public class RuleEngine {

	private final Logger logger = LoggerFactory.getLogger(RuleEngine.class);
	private Collection<PaymentBehavior> paymentBehaviors;

	public RuleEngine(Collection<PaymentBehavior> paymentBehaviors) {
		Preconditions.checkNotNull(paymentBehaviors);
		this.paymentBehaviors = paymentBehaviors;
	}

	public void pay(User customer, Product product) {
		logger.debug("Customer paid");
		customer.purchase(product);
		StreamSupport.stream(paymentBehaviors.spliterator(), false)
				.filter(processor -> processor.isApplicable(product))
				.forEach(processor -> processor.pay(customer, product));
	}
}
