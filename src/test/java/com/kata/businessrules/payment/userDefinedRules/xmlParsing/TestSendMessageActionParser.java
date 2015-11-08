package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.w3c.dom.Element;

import com.kata.businessrules.User;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class TestSendMessageActionParser {

	private Parser<Message> messageParser;
	private SendMessageActionParser parser;
	private Parser<Selector<User>> userSelectorParser;
	private IsMessage isMessage;

	private class IsMessage extends ArgumentMatcher<Element> {
		public boolean matches(Object obj) {
			return "message".equalsIgnoreCase(((Element) obj).getTagName());
		}
	}

	private void setCanParseMessage() {
		when(messageParser.canParse(argThat(isMessage))).thenReturn(true);
	}

	private void setCannotParseMessage() {
		when(messageParser.canParse(any(Element.class))).thenReturn(false);
	}

	private void setCanParseReceiver(Element xml) {
		when(userSelectorParser.canParse(xml)).thenReturn(true);
	}

	private void setCannotParseReceiver(Element xml) {
		when(userSelectorParser.canParse(xml)).thenReturn(false);
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		messageParser = (Parser<Message>) mock(Parser.class);
		userSelectorParser = (Parser<Selector<User>>) mock(Parser.class);
		parser = new SendMessageActionParser(messageParser, userSelectorParser);
		isMessage = new IsMessage();
	}

	@Test
	public void canParse_canParseReceiverAndMessage_true() throws Exception {
		Element xml = XmlElement.fromText("<sendMessage receiver=\"customer\">"
				+ "<message>hello</message>" + "</sendMessage>");
		setCanParseMessage();
		setCanParseReceiver(xml);
		ParserAssert.canParse(parser, xml);
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, (Element) null);
	}

	@Test
	public void canParse_cannotParseMessage_false() throws Exception {
		Element xml = XmlElement
				.fromText("<sendMessage receiver=\"customer\"/>");
		setCannotParseMessage();
		setCanParseReceiver(xml);
		ParserAssert.cannotParse(parser, xml);
	}

	@Test
	public void canParse_cannotParseReceiver_false() throws Exception {
		Element xml = XmlElement.fromText(
				"<sendMessage><message>hello</message></sendMessage>");
		setCanParseMessage();
		setCannotParseReceiver(xml);
		ParserAssert.cannotParse(parser, xml);
	}

	@Test
	public void canParse_notSendMessageAction_false() throws Exception {
		Element xml = XmlElement.fromText(
				"<SOMETHING_ELSE><message>hello</message></SOMETHING_ELSE>");
		setCanParseMessage();
		setCanParseReceiver(xml);
		ParserAssert.cannotParse(parser, xml);
	}
}
