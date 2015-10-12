package com.kata.businessrules;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.products.Product;

import static org.mockito.Mockito.*;

import java.util.Arrays;

public class TestRuleEngine {
	private RuleEngine engine;
	private CurrentUsers currentUsers;
	private Product product;
	private PaymentBehavior firstBehaviorThatCanProcessProduct;
	private PaymentBehavior secondBehaviorThatCanProcessProduct;
	private PaymentBehavior behaviorThatCannotProcessProduct;

	private void pay() {
		engine.pay(currentUsers, product);
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
		currentUsers = new CurrentMockedUsers();
		engine = new RuleEngine(
				Arrays.asList(firstBehaviorThatCanProcessProduct,
						behaviorThatCannotProcessProduct,
						secondBehaviorThatCanProcessProduct));
	}

	@Test
	public void pay_requestIsDispatchedToProperProcessor() {
		pay();
		verify(firstBehaviorThatCanProcessProduct, times(1)).pay(currentUsers,
				product);
		verify(secondBehaviorThatCanProcessProduct, times(1)).pay(currentUsers,
				product);
		verify(behaviorThatCannotProcessProduct, never()).pay(currentUsers,
				product);
	}

	@Test
	public void pay_productIsAddedToUsersListOfBoughtProducts() {		
		pay();
		verify(currentUsers.getCustomer()).purchase(product);
	}
}
