package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.ActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptAction;

public class IssueReceiptActionParser implements Parser<Action> {

	private ActionFactory<IssueReceiptAction> factory;

	public IssueReceiptActionParser(ActionFactory<IssueReceiptAction> factory) {
		Preconditions.checkNotNull(factory);
		this.factory = factory;
	}

	@Override
	public Action parse(Element element) {
		Preconditions.checkNotNull(element);
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
		boolean hasReceiverId = !Strings
				.isNullOrEmpty(element.getAttribute("receiverId"));
		boolean hasCorrectNumberOfAttributes = element.getAttributes()
				.getLength() == 2;
		return isNameCorrect && (isReceiverTheCustomer || hasReceiverId)
				&& hasCorrectNumberOfAttributes;
	}

}
