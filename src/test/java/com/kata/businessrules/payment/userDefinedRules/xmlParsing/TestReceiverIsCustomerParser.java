package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.kata.businessrules.User;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class TestReceiverIsCustomerParser {
	private ReceiverIsCustomerParser parser;

	@Before
	public void setup() {		
		parser = new ReceiverIsCustomerParser();
	}

	@Test
	public void parse_validElement_identitySelector() throws Exception {
		Selector<User> selector = parser.parse(XmlElement
				.fromText("<does_not_matter receiver=\"customer\" />"));
		User user = mock(User.class);
		assertThat(selector.select(user), is(user));
	}

	@Test
	public void canParse_receiverIsCustomer_true() throws Exception {
		ParserAssert.canParse(parser,
				"<does_not_matter receiver=\"customer\" />");
	}

	@Test
	public void canParse_receiverIsNotCustomer_false()
			throws Exception {
		ParserAssert.cannotParse(parser,
				"<does_not_matter receiver=\"not_customer\" />");
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, (Element) null);
	}
}
