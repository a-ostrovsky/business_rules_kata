package com.kata.businessrules.payment;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.CommunicationMethod;
import com.kata.businessrules.contact.MembershipActivationMessage;
import com.kata.businessrules.products.Membership;
import com.kata.businessrules.products.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TestActivationMessageForMembershipBehavior {
	private ActivationMessageForMembershipBehavior behavior;
	private CommunicationMethod communicationMethod;
	private Membership membership;
	private User customer;

	private void pay() {
		behavior.pay(customer, membership);
	}

	private void verifyCustomerHasReceivedMessage() {
		MembershipActivationMessage message = new MembershipActivationMessage(
				customer, membership);
		verify(communicationMethod).sendMessage(customer, message);
	}
	
	@Before
	public void Setup() {
		customer = mock(User.class);
		communicationMethod = mock(CommunicationMethod.class);
		behavior = new ActivationMessageForMembershipBehavior(communicationMethod);
		membership = (Membership)ProductFixture.createSomeMembership();
	}

	@Test
	public void pay_Membership_messageIsSentToCustomer() {
		pay();
		verifyCustomerHasReceivedMessage();
	}

	@Test
	public void isApplicable_onlyTrueForMemberships() {
		Product nonMembership = mock(Product.class);
		Product upgradedMembership = ProductFixture
				.createSomeUpgradedMembership();

		assertThat("Must be applicable to membership.",
				behavior.isApplicable(membership), is(true));
		assertThat("Must be applicable to upgraded membership.",
				behavior.isApplicable(upgradedMembership), is(true));
		assertThat("Must not be applicable to non membership.",
				behavior.isApplicable(nonMembership), is(false));
	}
}
