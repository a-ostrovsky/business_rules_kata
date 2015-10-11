package com.kata.businessrules;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.payment.PaymentProcessor;
import com.kata.businessrules.products.Product;

import static org.mockito.Mockito.*;

import java.util.Arrays;

public class TestRuleEngine {
	private RuleEngine engine;
	private CurrentUsers currentUsers;
	private Product product;
	private PaymentProcessor firstProcessorThatCanProcessProduct;
	private PaymentProcessor secondProcessorThatCanProcessProduct;
	private PaymentProcessor processorThatCannotProcessProduct;

	private void pay() {
		engine.pay(currentUsers, product);
	}

	@Before
	public void setup() {
		product = ProductFixture.createArbitraryProduct();
		firstProcessorThatCanProcessProduct = PaymentProcessorFixture
				.thatCanProcess(product);
		secondProcessorThatCanProcessProduct = PaymentProcessorFixture
				.thatCanProcess(product);
		processorThatCannotProcessProduct = PaymentProcessorFixture
				.thatCanNotProcess(product);
		currentUsers = new CurrentMockedUsers();
		engine = new RuleEngine(
				Arrays.asList(firstProcessorThatCanProcessProduct,
						processorThatCannotProcessProduct,
						secondProcessorThatCanProcessProduct));
	}

	@Test
	public void pay_requestIsDispatchedToProperProcessor() {
		pay();
		verify(firstProcessorThatCanProcessProduct, times(1)).pay(currentUsers,
				product);
		verify(secondProcessorThatCanProcessProduct, times(1)).pay(currentUsers,
				product);
		verify(processorThatCannotProcessProduct, never()).pay(currentUsers,
				product);
	}

	@Test
	public void pay_productIsAddedToUsersListOfBoughtProducts() {		
		pay();
		verify(currentUsers.getCustomer()).purchase(product);
	}
}
