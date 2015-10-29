package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;

import com.kata.businessrules.contact.Message;

import static org.mockito.Mockito.*;

public class TestSendMessageActionParser {

	private Parser<Message> messageParser;
	private SendMessageActionParser parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		messageParser = (Parser<Message>) mock(Parser.class);
		//parser = new SendMessageActionParser(messageParser);
	}

	public void canParse_sendMessageWithReceiverAndMessage_true(){
		
	}
}
