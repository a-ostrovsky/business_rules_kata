package com.kata.businessrules.payment.userDefinedRules.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kata.businessrules.User;
import com.kata.businessrules.contact.CommunicationMethod;
import com.kata.businessrules.contact.Message;

public class TestSendMessageAction {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private Action action;
	private CommunicationMethod communicationMethod;
	private Message message;
	private User customer;
	private User messageReceiver;

	private void verifyMessageHasBeenReceivedBy(User expectedReceiver) {
		verify(communicationMethod).sendMessage(expectedReceiver, message);
	}

	@Before
	public void Setup() {
		customer = mock(User.class);
		communicationMethod = mock(CommunicationMethod.class);
		message = mock(Message.class);
		action = new SendMessageAction(communicationMethod, message,
				new SelectorWithFixedResult<User>(customer, messageReceiver));
	}

	@Test
	public void execute_messageIsSent() {
		action.execute(customer, null);
		verifyMessageHasBeenReceivedBy(messageReceiver);
	}
}
