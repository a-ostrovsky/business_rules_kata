package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;

public class TestIssueReceiptActionParser {
	private IssueReceiptActionParserContext parserContext;

	@Before
	public void setup() {
		parserContext = new IssueReceiptActionParserContext();
	}

	@Test
	public void canParse_canParseReceiverAndProduct_true() throws Exception {
		parserContext.setXml(
				"<receiptFor product=\"bought\" receiver=\"customer\" />");
		parserContext.setSomeParserCanParseProduct();
		parserContext.setSomeParserCanParseReceiver();
		parserContext.verifyCanParse();
	}

	@Test
	public void canParse_canNotParseReceiver_false() throws Exception {
		parserContext.setXml("<receiptFor product=\"bought\" />");
		parserContext.setSomeParserCanParseProduct();
		parserContext.setNoParsersCanParseReceiver();
		parserContext.verifyCannotParse();
	}

	@Test
	public void canParse_canNotParseProduct_false() throws Exception {
		parserContext.setXml("<receiptFor receiver=\"customer\" />");
		parserContext.setSomeParserCanParseReceiver();
		parserContext.setNoParsersCanParseProduct();
		parserContext.verifyCannotParse();
	}

	@Test
	public void canParse_notReceiptForAction_false() throws Exception {
		parserContext.setXml(
				"<SOMETHING_ELSE product=\"bought\" receiver=\"customer\" />");
		parserContext.setSomeParserCanParseProduct();
		parserContext.setSomeParserCanParseReceiver();
		parserContext.verifyCannotParse();
	}

	@Test
	public void canParse_null_false() throws Exception {
		parserContext.setXml(null);
		parserContext.verifyCannotParse();
	}

	/*
	 * @Test public void canParse_receiverAttributeIsNotCustomer_false() throws
	 * Exception { ParserAssert.cannotParse(parser,
	 * "<receiptFor product=\"bought\" receiver=\"something_else\" />"); }
	 * 
	 * @Test public void canParse_receiverIdIsGiven_false() throws Exception {
	 * ParserAssert.canParse(parser,
	 * "<receiptFor product=\"bought\" receiverId=\"royaltyDepartment\" />"); }
	 * 
	 * @Test public void
	 * canParse_thereAreAnotherAttriburesThanReceiverAndProduct_false() throws
	 * Exception { ParserAssert.cannotParse(parser,
	 * "<receiptFor product=\"bought\" receiver=\"customer\" " +
	 * "somethingElse=\"true\" />"); }
	 * 
	 * @Test public void canParse_productIsMissing_false() throws Exception {
	 * ParserAssert.cannotParse(parser, "<receiptFor receiver=\"customer\" />");
	 * }
	 * 
	 */
}
