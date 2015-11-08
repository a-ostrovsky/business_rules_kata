package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;
import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class SendMessageActionParser implements Parser<Action> {

	private Parser<Message> messageParser;
	private Parser<Selector<User>> userSelectorParser;

	public SendMessageActionParser(Parser<Message> messageParser,
			Parser<Selector<User>> userSelectorParser) {
		Preconditions.checkNotNull(messageParser);
		Preconditions.checkNotNull(userSelectorParser);
		this.messageParser = messageParser;
		this.userSelectorParser = userSelectorParser;
	}

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
		boolean isNameCorrect = "sendMessage"
				.equalsIgnoreCase(element.getTagName());
		Element message = (Element) element.getElementsByTagName("message")
				.item(0);
		boolean canParseMessage = messageParser.canParse(message);
		boolean canParseReceiver = userSelectorParser.canParse(element);
		return isNameCorrect && canParseMessage && canParseReceiver;
	}

}
