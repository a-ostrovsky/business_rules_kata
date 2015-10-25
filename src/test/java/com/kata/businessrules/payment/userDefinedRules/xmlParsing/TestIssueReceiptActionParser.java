package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.payment.userDefinedRules.FixedActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.ActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptAction;

import static org.mockito.Mockito.*;

public class TestIssueReceiptActionParser {
	private IssueReceiptActionParser parser;
	private ActionFactory<IssueReceiptAction> factory;
	private IssueReceiptAction action;

	@Before
	public void setup() {
		action = mock(IssueReceiptAction.class);
		factory = new FixedActionFactory<IssueReceiptAction>(action);
		parser = new IssueReceiptActionParser(factory);
	}

	@Test
	public void canParse_receiverAttributeIsCustomer_true() throws Exception {
		ParserAssert.canParse(parser,
				"<receiptFor product=\"bought\" receiver=\"customer\" />");
	}

	@Test
	public void canParse_receiverAttributeIsNotCustomer_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<receiptFor product=\"bought\" receiver=\"something_else\" />");
	}
	@Test
	public void canParse_receiverIdIsGiven_false() throws Exception {
		ParserAssert.canParse(parser,
				"<receiptFor product=\"bought\" receiverId=\"royaltyDepartment\" />");
	}
	@Test
	public void canParse_thereAreAnotherAttriburesThanReceiverAndProduct_false()
			throws Exception {
		ParserAssert.cannotParse(parser,
				"<receiptFor product=\"bought\" receiver=\"customer\" "
						+ "somethingElse=\"true\" />");
	}

	@Test
	public void canParse_productIsMissing_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<receiptFor receiver=\"customer\" />");
	}

	@Test
	public void canParse_notReceiptForAction_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<SOMETHING_ELSE product=\"bought\" receiver=\"customer\" />");
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, null);
	}
}
