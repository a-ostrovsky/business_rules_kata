package com.kata.businessrules;

import java.util.Collection;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.products.Product;

public class RuleEngineForPaymentBehaviors implements RuleEngine {

	private final Logger logger = LoggerFactory.getLogger(RuleEngineForPaymentBehaviors.class);
	private Collection<PaymentBehavior> paymentBehaviors;

	@Inject
	public RuleEngineForPaymentBehaviors(Collection<PaymentBehavior> paymentBehaviors) {
		Preconditions.checkNotNull(paymentBehaviors);
		this.paymentBehaviors = paymentBehaviors;
	}

	@Override
	public void pay(User customer, Product product) {
		logger.debug("Customer paid");
		customer.purchase(product);
		StreamSupport.stream(paymentBehaviors.spliterator(), false)
				.filter(processor -> processor.isApplicable(product))
				.forEach(processor -> processor.pay(customer, product));
	}
}
