package com.kata.businessrules.payment.userDefinedRules.actions;

import com.kata.businessrules.User;
import com.kata.businessrules.contact.Message;

public interface SendMessageActionFactory {
	Action create(Selector<User> userSelector, Message message);
}
