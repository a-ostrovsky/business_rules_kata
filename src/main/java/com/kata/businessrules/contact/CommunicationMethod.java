package com.kata.businessrules.contact;

import com.kata.businessrules.User;

public interface CommunicationMethod {
	void sendMessage(User user, Message message);
}
