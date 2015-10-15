package com.kata.businessrules.contact;

import com.kata.businessrules.User;

public interface Contact {
	void sendMessage(User user, Message message);
}
