package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.kata.businessrules.User;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class ReceiverIsCustomerParser implements Parser<Selector<User>> {

	@Override
	public Selector<User> parse(Element element) {
		return customer -> customer;
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		String receiver = element.getAttribute("receiver");
		return "customer".equalsIgnoreCase(receiver);
	}

}
