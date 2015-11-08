package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;
import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.payment.userDefinedRules.actions.SendMessageActionFactory;

public class SendMessageActionParser implements Parser<Action> {

	private Parser<Message> messageParser;
	private Parser<Selector<User>> userSelectorParser;
	private SendMessageActionFactory factory;

	public SendMessageActionParser(SendMessageActionFactory factory,
			Parser<Message> messageParser,
			Parser<Selector<User>> userSelectorParser) {
		Preconditions.checkNotNull(messageParser);
		Preconditions.checkNotNull(userSelectorParser);
		Preconditions.checkNotNull(factory);
		this.messageParser = messageParser;
		this.userSelectorParser = userSelectorParser;
		this.factory = factory;
	}

	@Override
	public Action parse(Element element) {
		Preconditions.checkNotNull(element);
		Selector<User> userSelector = userSelectorParser.parse(element);
		Element messageElement = getMessageElement(element);
		Message message = messageParser.parse(messageElement);
		return factory.create(userSelector, message);
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		boolean isNameCorrect = "sendMessage"
				.equalsIgnoreCase(element.getTagName());
		Element message = getMessageElement(element);
		boolean canParseMessage = messageParser.canParse(message);
		boolean canParseReceiver = userSelectorParser.canParse(element);
		return isNameCorrect && canParseMessage && canParseReceiver;
	}
	
	private Element getMessageElement(Element element) {
		Element messageElement = (Element) element
				.getElementsByTagName("message").item(0);
		return messageElement;
	}
}
