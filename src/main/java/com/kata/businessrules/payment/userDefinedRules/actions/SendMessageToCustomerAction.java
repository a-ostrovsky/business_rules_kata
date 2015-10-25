package com.kata.businessrules.payment.userDefinedRules.actions;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Contact;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.products.Product;

public class SendMessageToCustomerAction implements Action {

	private Contact contact;
	private Message message;
	
	public SendMessageToCustomerAction(Contact contact, Message message) {
		Preconditions.checkNotNull(contact);
		Preconditions.checkNotNull(message);
		this.message = message; 
		this.contact = contact;
	}

	@Override
	public void execute(User customer, Product product) {
		Preconditions.checkNotNull(customer);		
		contact.sendMessage(customer, message);
	}

}
