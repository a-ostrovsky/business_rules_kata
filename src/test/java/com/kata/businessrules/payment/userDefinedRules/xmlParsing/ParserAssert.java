package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.kata.businessrules.XmlElement;

public class ParserAssert {

	private static <T> void canParse(Parser<T> parser,
			boolean expectedCanParse, String xmlElement) throws Exception {
		assertThat(parser.canParse(XmlElement.fromText(xmlElement)),
				is(expectedCanParse));
	}

	public static <T> void canParse(Parser<T> parser, String xmlElement)
			throws Exception {
		canParse(parser, true, xmlElement);
	}
	
	public static <T> void cannotParse(Parser<T> parser, String xmlElement)
			throws Exception {
		canParse(parser, false, xmlElement);
	}

}
