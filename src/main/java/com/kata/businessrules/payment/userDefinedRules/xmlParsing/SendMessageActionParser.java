package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Collection;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class SendMessageActionParser implements Parser<Action> {

	private Parser<Message> messageParser;

	public SendMessageActionParser(Parser<Message> messageParser,
			//TODO: Collection<Parser<Selector<User>>> -> Parser<Selector<User>> that has
			//the selection logic inside
			Collection<Parser<Selector<User>>> userSelectorParsers) {
		this.messageParser = messageParser;
		Preconditions.checkNotNull(messageParser);		
	}
	
	@Override
	public Action parse(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canParse(Element element) {
		// TODO Auto-generated method stub
		return false;
	}

}
