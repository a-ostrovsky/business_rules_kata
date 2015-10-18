package com.kata.businessrules.payment.userDefinedRules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.User;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;
import com.kata.businessrules.products.Product;

public class TestUserDefinedRuleWithOneAction {
	private PaymentBehavior behavior;
	private Product product;
	private User customer;
	private Filter filter;
	private Action action;
	private Map<String, Object> namedParameters;

	@Before
	public void setup() {
		filter = mock(Filter.class);
		action = mock(Action.class);
		customer = mock(User.class);
		namedParameters = new HashMap<String, Object>();
		behavior = new UserDefinedRuleWithOneAction(filter, action,
				namedParameters);
		product = ProductFixture.createArbitraryProduct();
	}

	@Test
	public void pay_actionIsExecuted() {
		behavior.pay(customer, product);
		verify(action).execute(customer, product, namedParameters);
	}

	@Test
	public void isApplicable_onlyTrueWhenFilterReturnsTrue() {
		Product nonApplicableProduct = mock(Product.class);
		when(filter.isApplicable(nonApplicableProduct)).thenReturn(false);
		when(filter.isApplicable(product)).thenReturn(true);

		assertThat("Must return true for applicable product.",
				behavior.isApplicable(product), is(true));
		assertThat("Must return false for non applicable product.",
				behavior.isApplicable(nonApplicableProduct), is(false));
	}
}
