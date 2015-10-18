package com.kata.businessrules.payment.userDefinedRules.actions;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Contact;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.products.Product;

public class SendMessageToCustomerAction implements Action {

	private Contact contact;

	public SendMessageToCustomerAction(Contact contact) {
		Preconditions.checkNotNull(contact);
		this.contact = contact;
	}

	@Override
	public void execute(User customer, Product product,
			Map<String, Object> namedParameters) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(namedParameters);
		Message message = (Message)namedParameters.get("message");
		contact.sendMessage(customer, message);
	}

}
