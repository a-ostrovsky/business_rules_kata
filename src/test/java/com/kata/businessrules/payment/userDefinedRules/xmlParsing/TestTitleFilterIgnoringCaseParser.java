package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;

public class TestTitleFilterIgnoringCaseParser {
	private TitleFilterIgnoringCaseParser parser;

	@Before
	public void setup() {
		parser = new TitleFilterIgnoringCaseParser();
	}

	@Test
	public void canParse_onlyTitleAttribute_true() throws Exception {
		ParserAssert.canParse(parser,
				"<whenPaidFor productTitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_noTitleAttribute_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<whenPaidFor productType=\"Membership\"/>");
	}

	@Test
	public void canParse_productTitleAttributeInCombinationWithAnotherAttribute_false()
			throws Exception {
		ParserAssert.cannotParse(parser,
				"<whenPaidFor productType=\"Video\" productTtitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_notWhenPaidForFilter_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<SOMETHING_ELSE productTitle=\"Video\"/>");
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, null);
	}
}
