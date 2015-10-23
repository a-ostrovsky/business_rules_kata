package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.kata.businessrules.payment.userDefinedRules.actions.Action;

public class ReceiptToCustomerActionParser implements Parser<Action> {

	@Override
	public Action parse(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		boolean isNameCorrect = "receiptFor"
				.equalsIgnoreCase(element.getTagName());
		String receiver = element.getAttribute("receiver");
		boolean isReceiverTheCustomer = "customer".equalsIgnoreCase(receiver);
		boolean hasCorrectNumberOfAttributes = element.getAttributes()
				.getLength() == 2;
		return isNameCorrect && isReceiverTheCustomer
				&& hasCorrectNumberOfAttributes;
	}

}
