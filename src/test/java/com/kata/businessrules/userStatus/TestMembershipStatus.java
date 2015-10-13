package com.kata.businessrules.userStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.CustomerFixture;
import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public class TestMembershipStatus {
	private MembershipStatus status;
	private User customer;

	@Before
	public void setup() {
		status = new MembershipStatus();
		customer = CustomerFixture.createCustomer();
	}

	@Test
	public void hasStatus_customerHasNeverBoughtAMembership_false() {
		Product notAMembership = ProductFixture.createSomeBook();
		customer.purchase(notAMembership);
		assertThat(status.hasStatus(customer), is(false));
	}

	@Test
	public void hasStatus_customerHasBoughtAMembership_true() {
		Product membership = ProductFixture.createSomeMembership();
		customer.purchase(membership);
		assertThat(status.hasStatus(customer), is(true));
	}
}
