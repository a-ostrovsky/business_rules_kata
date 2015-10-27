package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.User;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class TestIssueReceiptActionParser {
	private IssueReceiptActionParserContext parserContext;
	private IssueReceiptActionParser parser;
	private IssueReceiptActionFactory actionFactory;

	private Action setupIssueReceiptActionFactory(Selector<User> userSelector,
			Selector<Product> productSelector) {
		Action result = mock(Action.class);
		when(actionFactory.create(userSelector, productSelector))
				.thenReturn(result);
		return result;
	}

	private static Selector<User> setupUserSelector(
			IssueReceiptActionParserContext context) {
		Parser<Selector<User>> userSelectorParser = context
				.setSomeParserCanParseReceiver();
		Selector<User> userSelector = userSelectorParser
				.parse(context.getXml());
		return userSelector;
	}

	private static Selector<Product> setupProductSelector(
			IssueReceiptActionParserContext context) {
		Parser<Selector<Product>> productSelectorParser = context
				.setSomeParserCanParseProduct();
		Selector<Product> productSelector = productSelectorParser
				.parse(context.getXml());
		return productSelector;
	}

	@Before
	public void setup() {
		actionFactory = mock(IssueReceiptActionFactory.class);
		parserContext = new IssueReceiptActionParserContext(actionFactory);
		parser = parserContext.getParser();
	}

	@Test
	public void parse_canParse_IssueReceiptActionAsDescribedInXml()
			throws Exception {
		parserContext.setXml(
				"<receiptFor product=\"bought\" receiver=\"customer\" />");
		Selector<Product> productSelector = setupProductSelector(parserContext);
		Selector<User> userSelector = setupUserSelector(parserContext);
		Action expectedResult = setupIssueReceiptActionFactory(userSelector,
				productSelector);
		Action action = parser.parse(parserContext.getXml());
		assertThat(action, is(expectedResult));
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
