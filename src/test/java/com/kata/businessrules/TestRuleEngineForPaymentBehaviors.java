package com.kata.businessrules;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.products.Product;

import static org.mockito.Mockito.*;

import java.util.Arrays;

public class TestRuleEngineForPaymentBehaviors {
	private RuleEngine behaviors;
	private Product product;
	private PaymentBehavior firstBehaviorThatCanProcessProduct;
	private PaymentBehavior secondBehaviorThatCanProcessProduct;
	private PaymentBehavior behaviorThatCannotProcessProduct;
	private User customer;

	private void pay() {
		behaviors.pay(customer, product);
	}

	@Before
	public void setup() {
		product = ProductFixture.createArbitraryProduct();
		firstBehaviorThatCanProcessProduct = PaymentProcessorFixture
				.thatCanProcess(product);
		secondBehaviorThatCanProcessProduct = PaymentProcessorFixture
				.thatCanProcess(product);
		behaviorThatCannotProcessProduct = PaymentProcessorFixture
				.thatCanNotProcess(product);
		customer = mock(User.class);
		behaviors = new RuleEngineForPaymentBehaviors(
				Arrays.asList(firstBehaviorThatCanProcessProduct,
						behaviorThatCannotProcessProduct,
						secondBehaviorThatCanProcessProduct));
	}

	@Test
	public void pay_requestIsDispatchedToProperProcessor() {
		pay();
		verify(firstBehaviorThatCanProcessProduct, times(1)).pay(customer,
				product);
		verify(secondBehaviorThatCanProcessProduct, times(1)).pay(customer,
				product);
		verify(behaviorThatCannotProcessProduct, never()).pay(customer,
				product);
	}

	@Test
	public void pay_productIsAddedToUsersListOfBoughtProducts() {
		pay();
		verify(customer).purchase(product);
	}
}
