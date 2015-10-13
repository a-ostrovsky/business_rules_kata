package com.kata.businessrules.userStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.CustomerFixture;
import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public class TestUpgradedMembershipStatus {
	private Status status;
	private User customer;

	@Before
	public void setup() {
		status = new UpgradedMembershipStatus();
		customer = CustomerFixture.createCustomer();
	}

	@Test
	public void hasStatus_customerHasNeverBoughtAMembership_false() {
		Product notAnUpgradedMembership = ProductFixture.createSomeBook();
		customer.purchase(notAnUpgradedMembership);
		assertThat(status.hasStatus(customer), is(false));
	}

	@Test
	public void hasStatus_customerHasBoughtAMembership_true() {
		Product upgradedMembership = ProductFixture.createSomeUpgradedMembership();
		customer.purchase(upgradedMembership);
		assertThat(status.hasStatus(customer), is(true));
	}
}
