package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.payment.userDefinedRules.actions.SendMessageAction;
import com.kata.businessrules.payment.userDefinedRules.actions.SendMessageActionFactory;

public class TestSendMessageActionParser {

	private Parser<Message> messageParser;
	private SendMessageActionParser parser;
	private Parser<Selector<User>> userSelectorParser;
	private IsMessage isMessage;
	private Message message;
	private Selector<User> userSelector;
	private SendMessageActionFactory actionFactory;

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

	private SendMessageAction setupSendMessageActionFactory(Selector<User> userSelector,
			Message message) {
		SendMessageAction result = mock(SendMessageAction.class);
		when(actionFactory.create(userSelector, message)).thenReturn(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		messageParser = (Parser<Message>) mock(Parser.class);
		userSelectorParser = (Parser<Selector<User>>) mock(Parser.class);
		message = mock(Message.class);
		userSelector = (Selector<User>) mock(Selector.class);
		actionFactory = mock(SendMessageActionFactory.class);
		parser = new SendMessageActionParser(actionFactory, messageParser,
				userSelectorParser);
		isMessage = new IsMessage();
	}

	@Test
	public void parse_canParse_SendMessageActionAsDescribedInXml()
			throws Exception {
		Element xml = XmlElement.fromText("<sendMessage receiver=\"customer\">"
				+ "<message>hello</message>" + "</sendMessage>");
		when(messageParser.parse(argThat(isMessage))).thenReturn(message);
		when(userSelectorParser.parse(xml)).thenReturn(userSelector);
		Action expectedResult = setupSendMessageActionFactory(userSelector,
				message);
		Action action = parser.parse(xml);
		assertThat(action, is(expectedResult));
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
