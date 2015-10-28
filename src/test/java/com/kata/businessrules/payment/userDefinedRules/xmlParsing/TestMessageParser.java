package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class TestMessageParser {
	
	private MessageParser parser;

	@Before
	public void setup() {
		parser = new MessageParser();
	}
	
	@Test
	public void canParse_messageElement_true() throws Exception {
		ParserAssert.canParse(parser, "<message>Hello</message>");
	}
	
	@Test
	public void canParse_notMessageElement_false() throws Exception {
		ParserAssert.cannotParse(parser, "<something_else>Hello</something_else>");
	}
	
	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, (Element)null);
	}
}
