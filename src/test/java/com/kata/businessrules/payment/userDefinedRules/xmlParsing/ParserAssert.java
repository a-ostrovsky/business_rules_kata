package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.w3c.dom.Element;

import com.kata.businessrules.XmlElement;

public class ParserAssert {

	private static <T> void canParse(Parser<T> parser, boolean expectedCanParse,
			String xmlElement) throws Exception {		
		canParse(parser, expectedCanParse, XmlElement.fromText(xmlElement));
	}

	private static <T> void canParse(Parser<T> parser, boolean expectedCanParse,
			Element xmlElement) throws Exception {
		assertThat(parser.canParse(xmlElement), is(expectedCanParse));
	}

	public static <T> void canParse(Parser<T> parser, String xmlElement)
			throws Exception {
		canParse(parser, true, xmlElement);
	}
	
	public static <T> void canParse(Parser<T> parser, Element xmlElement)
			throws Exception {
		canParse(parser, true, xmlElement);
	}

	public static <T> void cannotParse(Parser<T> parser, String xmlElement)
			throws Exception {
		canParse(parser, false, xmlElement);
	}
	
	public static <T> void cannotParse(Parser<T> parser, Element xmlElement)
			throws Exception {
		canParse(parser, false, xmlElement);
	}

}
