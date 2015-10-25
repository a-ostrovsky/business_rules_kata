package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.payment.userDefinedRules.FixedActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.ActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.ReceiptToCustomerAction;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TestReceiptToCustomerActionParser {
	private ReceiptToCustomerActionParser parser;
	private ActionFactory<ReceiptToCustomerAction> factory;
	private ReceiptToCustomerAction action;

	@Before
	public void setup() {
		action = mock(ReceiptToCustomerAction.class);
		factory = new FixedActionFactory<ReceiptToCustomerAction>(action);
		parser = new ReceiptToCustomerActionParser(factory);
	}

	@Test
	public void canParse_receiverIsCustomer_true() throws Exception {
		ParserAssert.canParse(parser,
				"<receiptFor product=\"bought\" receiver=\"customer\" />");
	}

	@Test
	public void canParse_receiverIsNotCustomer_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<receiptFor product=\"bought\" receiver=\"royaltyDepartment\" />");
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
