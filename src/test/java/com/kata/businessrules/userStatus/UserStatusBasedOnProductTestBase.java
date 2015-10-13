package com.kata.businessrules.userStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.CustomerFixture;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public abstract class UserStatusBasedOnProductTestBase {
	private Status status;
	private User customer;

	protected abstract Status createStatus();

	protected abstract Product createStatusProduct();

	protected abstract Product createNonStatusProduct();

	@Before
	public void setup() {
		status = createStatus();
		customer = CustomerFixture.createCustomer();
	}

	@Test
	public void hasStatus_customerHasNeverBoughtStatusProduct_false() {
		Product notAStatusProduct = createNonStatusProduct();
		customer.purchase(notAStatusProduct);
		assertThat(
				String.format(
						"Buying a non status product must not lead to having status %s",
						status.getClass().getSimpleName()),
				status.hasStatus(customer), is(false));
	}

	@Test
	public void hasStatus_customerHasBoughtAStatusProduct_true() {
		Product statusProduct = createStatusProduct();
		customer.purchase(statusProduct);
		assertThat(
				String.format(
						"Buying a status product %s must lead to having status %s",
						statusProduct.getClass().getSimpleName(),
						status.getClass().getSimpleName()),
				status.hasStatus(customer), is(true));
	}
}
