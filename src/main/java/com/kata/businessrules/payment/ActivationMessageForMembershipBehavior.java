package com.kata.businessrules.payment;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Contact;
import com.kata.businessrules.contact.MembershipActivationMessage;
import com.kata.businessrules.products.Membership;
import com.kata.businessrules.products.Product;

public class ActivationMessageForMembershipBehavior implements PaymentBehavior {

	private Contact contact;

	public ActivationMessageForMembershipBehavior(Contact contact) {
		Preconditions.checkNotNull(contact);
		this.contact = contact;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pay(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		MembershipActivationMessage message = new MembershipActivationMessage(
				customer, (Membership) product);
		contact.sendMessage(customer, message);
	}

	@Override
	public boolean isApplicable(Product product) {
		return product instanceof Membership;
	}

}
