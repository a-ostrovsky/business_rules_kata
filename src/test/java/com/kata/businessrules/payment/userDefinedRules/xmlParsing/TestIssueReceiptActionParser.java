package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import com.kata.businessrules.User;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptAction;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class TestIssueReceiptActionParser {
	private IssueReceiptActionParser parser;
	private IssueReceiptActionFactory actionFactory;
	private Parser<Selector<User>> userSelectorParser;
	private Parser<Selector<Product>> productSelectorParser;
	private Selector<Product> productSelector;
	private Selector<User> userSelector;

	private IssueReceiptAction setupIssueReceiptActionFactory(
			Selector<User> userSelector, Selector<Product> productSelector) {
		IssueReceiptAction result = mock(IssueReceiptAction.class);
		when(actionFactory.create(userSelector, productSelector))
				.thenReturn(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		userSelector = (Selector<User>) mock(Selector.class);
		productSelector = (Selector<Product>) mock(Selector.class);
		actionFactory = mock(IssueReceiptActionFactory.class);
		userSelectorParser = (Parser<Selector<User>>) mock(Parser.class);
		productSelectorParser = (Parser<Selector<Product>>) mock(Parser.class);
		parser = new IssueReceiptActionParser(actionFactory, userSelectorParser,
				productSelectorParser);
	}

	@Test
	public void parse_canParse_IssueReceiptActionAsDescribedInXml()
			throws Exception {
		Element xml = XmlElement.fromText(
				"<receiptFor product=\"bought\" receiver=\"customer\" />");
		when(productSelectorParser.parse(xml)).thenReturn(productSelector);
		when(userSelectorParser.parse(xml)).thenReturn(userSelector);
		Action expectedResult = setupIssueReceiptActionFactory(userSelector,
				productSelector);
		Action action = parser.parse(xml);
		assertThat(action, is(expectedResult));
	}

	@Test
	public void canParse_canParseReceiverAndProduct_true() throws Exception {
		Element xml = XmlElement.fromText(
				"<receiptFor product=\"bought\" receiver=\"customer\" />");
		when(productSelectorParser.canParse(xml)).thenReturn(true);
		when(userSelectorParser.canParse(xml)).thenReturn(true);
		ParserAssert.canParse(parser, xml);
	}

	@Test
	public void canParse_canNotParseReceiver_false() throws Exception {
		Element xml = XmlElement.fromText("<receiptFor product=\"bought\" />");
		when(productSelectorParser.canParse(xml)).thenReturn(true);
		when(userSelectorParser.canParse(xml)).thenReturn(false);
		ParserAssert.cannotParse(parser, xml);
	}

	@Test
	public void canParse_canNotParseProduct_false() throws Exception {
		Element xml = XmlElement
				.fromText("<receiptFor receiver=\"customer\" />");
		when(productSelectorParser.canParse(xml)).thenReturn(false);
		when(userSelectorParser.canParse(xml)).thenReturn(true);
		ParserAssert.cannotParse(parser, xml);
	}

	@Test
	public void canParse_notReceiptForAction_false() throws Exception {
		Element xml = XmlElement.fromText(
				"<SOMETHING_ELSE product=\"bought\" receiver=\"customer\" />");
		when(productSelectorParser.canParse(xml)).thenReturn(true);
		when(userSelectorParser.canParse(xml)).thenReturn(true);
		ParserAssert.cannotParse(parser, xml);
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, (Element) null);
	}
}
