package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kata.businessrules.User;
import com.kata.businessrules.UserRepository;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class ReceiverWithIdParser implements Parser<Selector<User>> {

	private UserRepository userRepository;

	public ReceiverWithIdParser(UserRepository userRepository) {
		Preconditions.checkNotNull(userRepository);
		this.userRepository = userRepository;
	}

	@Override
	public Selector<User> parse(Element element) {
		Preconditions.checkNotNull(element);
		String receiverId = element.getAttribute("receiverId");
		return customer -> userRepository.getById(receiverId);
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		boolean hasReceiverId = !Strings
				.isNullOrEmpty(element.getAttribute("receiverId"));
		return hasReceiverId;
	}

}
