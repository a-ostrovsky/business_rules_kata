package com.kata.businessrules;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.PhysicalProduct;
import com.kata.businessrules.products.Product;

public class RuleEngine {

	private final Logger logger = LoggerFactory.getLogger(RuleEngine.class);
	private Iterable<PaymentProcessor> paymentProcessors;

	public RuleEngine(Iterable<PaymentProcessor> paymentProcessors) {
		Preconditions.checkNotNull(paymentProcessors);
		this.paymentProcessors = paymentProcessors;
	}

	public void pay(CurrentUsers users, Product product) {
		logger.debug("Customer paid");
		users.getCustomer().purchase(product);
		StreamSupport.stream(paymentProcessors.spliterator(), false)
				.filter(processor -> processor.canProcess(product))
				.forEach(processor -> processor.pay(users, product));
	}
}
