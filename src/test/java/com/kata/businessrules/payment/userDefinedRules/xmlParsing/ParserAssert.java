package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.kata.businessrules.XmlElement;

public class ParserAssert {

	private static void canParse(FilterParser parser,
			boolean expectedCanParse, String xmlElement) throws Exception {
		assertThat(parser.canParse(XmlElement.fromText(xmlElement)),
				is(expectedCanParse));
	}

	public static void canParse(FilterParser parser, String xmlElement)
			throws Exception {
		canParse(parser, true, xmlElement);
	}
	
	public static void cannotParse(FilterParser parser, String xmlElement)
			throws Exception {
		canParse(parser, false, xmlElement);
	}

}
