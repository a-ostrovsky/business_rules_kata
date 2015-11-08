package com.kata.businessrules.payment.userDefinedRules.actions;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.CommunicationMethod;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.products.Product;

public class SendMessageAction implements Action {

	private CommunicationMethod communicationMethod;
	private Message message;
	private Selector<User> userSelector;

	public SendMessageAction(CommunicationMethod communicationMethod, Message message,
			Selector<User> userSelector) {
		Preconditions.checkNotNull(communicationMethod);
		Preconditions.checkNotNull(message);
		Preconditions.checkNotNull(userSelector);
		this.message = message;
		this.communicationMethod = communicationMethod;
		this.userSelector = userSelector;
	}

	@Override
	public void execute(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		User receiver = userSelector.select(customer);
		communicationMethod.sendMessage(receiver, message);
	}

}
