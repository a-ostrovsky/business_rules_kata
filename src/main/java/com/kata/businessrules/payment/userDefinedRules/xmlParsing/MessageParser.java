package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.kata.businessrules.contact.Message;

public class MessageParser implements Parser<Message> {

	@Override
	public Message parse(Element element) {
		Preconditions.checkNotNull(element);
		return null;
	}

	@Override
	public boolean canParse(Element element) {
		if(element == null) {
			return false;
		}
		return "message".equalsIgnoreCase(element.getTagName());
	}

}
