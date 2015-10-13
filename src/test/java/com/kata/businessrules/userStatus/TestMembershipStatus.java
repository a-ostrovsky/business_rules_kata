package com.kata.businessrules.userStatus;

import org.junit.Before;
import org.junit.Test;
import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

public class TestMembershipStatus {
	private MembershipStatus status;
	private User customer;

	private void customerHasPurchased(Collection<Product> products) {
		when(customer.getPurchasedProducts()).thenReturn(products);
	}

	@Before
	public void setup() {
		status = new MembershipStatus();
		customer = mock(User.class);
	}

	@Test
	public void hasStatus_customerHasNeverBoughtAMembership_false() {
		Product notAMembership = ProductFixture.createSomeBook();
		customerHasPurchased(Arrays.asList(notAMembership));
		assertThat(status.hasStatus(customer), is(false));
	}

	@Test
	public void hasStatus_customerHasBoughtAMembership_true() {
		Product membership = ProductFixture.createSomeMembership();
		customerHasPurchased(Arrays.asList(membership));
		assertThat(status.hasStatus(customer), is(true));
	}
}
