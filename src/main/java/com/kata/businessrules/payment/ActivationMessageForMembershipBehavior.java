package com.kata.businessrules.payment;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.CommunicationMethod;
import com.kata.businessrules.contact.MembershipActivationMessage;
import com.kata.businessrules.products.Membership;
import com.kata.businessrules.products.Product;

public class ActivationMessageForMembershipBehavior implements PaymentBehavior {

	private CommunicationMethod communicationMethod;

	public ActivationMessageForMembershipBehavior(CommunicationMethod communicationMethod) {
		Preconditions.checkNotNull(communicationMethod);
		this.communicationMethod = communicationMethod; 
	}

	@Override
	public void pay(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		MembershipActivationMessage message = new MembershipActivationMessage(
				customer, (Membership) product);
		communicationMethod.sendMessage(customer, message);
	}

	@Override
	public boolean isApplicable(Product product) {
		return product instanceof Membership;
	}

}
