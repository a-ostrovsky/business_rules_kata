package com.kata.businessrules.payment.userDefinedRules.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kata.businessrules.User;
import com.kata.businessrules.contact.Contact;
import com.kata.businessrules.contact.Message;

public class TestSendMessageToCustomerAction {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private Action action;
	private Contact contact;
	private User customer;
	private Message message;

	private void verifyCustomerHasReceivedMessage() {
		verify(contact).sendMessage(customer, message);
	}

	@Before
	public void Setup() {
		customer = mock(User.class);
		contact = mock(Contact.class);
		message = mock(Message.class);
		action = new SendMessageToCustomerAction(contact);
	}

	@Test
	public void execute_messageIsProvided_messageIsSentToCustomer() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("message", message);
		action.execute(customer, null, parameters);
		verifyCustomerHasReceivedMessage();
	}

	@Test
	public void execute_messageIsNotProvided_IllegalArgumentExceptoin() {
		exception.expect(IllegalArgumentException.class);
		Map<String, Object> parametersWithoutMessage = new HashMap<String, Object>();
		action.execute(customer, null, parametersWithoutMessage);
	}	
}
