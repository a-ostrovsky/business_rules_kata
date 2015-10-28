package com.kata.businessrules.payment.userDefinedRules.actions;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Contact;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.products.Product;

public class SendMessageAction implements Action {

	private Contact contact;
	private Message message;
	private Selector<User> userSelector;

	public SendMessageAction(Contact contact, Message message,
			Selector<User> userSelector) {
		Preconditions.checkNotNull(contact);
		Preconditions.checkNotNull(message);
		Preconditions.checkNotNull(userSelector);
		this.message = message;
		this.contact = contact;
		this.userSelector = userSelector;
	}

	@Override
	public void execute(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		User receiver = userSelector.select(customer);
		contact.sendMessage(receiver, message);
	}

}
