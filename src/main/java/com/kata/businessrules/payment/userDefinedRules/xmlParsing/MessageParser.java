package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.kata.businessrules.contact.Message;

public class MessageParser implements Parser<Message> {

	@Override
	public Message parse(Element element) {
		// TODO: To return something more meaningful I need to know how a
		// message looks like. E.g. formatted / plain text, ...
		return new Message() {
		};
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		return "message".equalsIgnoreCase(element.getTagName());
	}

}
