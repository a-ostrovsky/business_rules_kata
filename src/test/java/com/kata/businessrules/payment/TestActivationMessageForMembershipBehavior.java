package com.kata.businessrules.payment;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Contact;
import com.kata.businessrules.contact.MembershipActivationMessage;
import com.kata.businessrules.products.Membership;
import com.kata.businessrules.products.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TestActivationMessageForMembershipBehavior {
	private ActivationMessageForMembershipBehavior behavior;
	private Contact contact;
	private Membership membership;
	private User customer;

	private void pay() {
		behavior.pay(customer, membership);
	}

	private void verifyCustomerHasReceivedMessage() {
		MembershipActivationMessage message = new MembershipActivationMessage(
				customer, membership);
		verify(contact).sendMessage(customer, message);
	}
	
	@Before
	public void Setup() {
		customer = mock(User.class);
		contact = mock(Contact.class);
		behavior = new ActivationMessageForMembershipBehavior(contact);
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
