package com.kata.businessrules.contact;

import com.google.inject.AbstractModule;
import com.kata.businessrules.User;

public class DummyContactModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CommunicationMethod.class).toInstance(new CommunicationMethod() {			
			@Override
			public void sendMessage(User user, Message message) {
				
			}
		});
	}

}
