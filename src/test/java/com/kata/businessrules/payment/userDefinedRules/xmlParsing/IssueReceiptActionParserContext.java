package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.kata.businessrules.User;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class IssueReceiptActionParserContext {

	private IssueReceiptActionParser parser;
	private List<Parser<Selector<User>>> receiverSelectorParsers;
	private List<Parser<Selector<Product>>> productSelectorParsers;
	private Element element;

	public Element getXml() {
		return element;
	}

	@SuppressWarnings("unchecked")
	private <T> Parser<Selector<T>> createParser() {
		return (Parser<Selector<T>>) mock(Parser.class);
	}

	public IssueReceiptActionParserContext(IssueReceiptActionFactory factory) {
		receiverSelectorParsers = Arrays.asList(createParser(), createParser());
		productSelectorParsers = Arrays.asList(createParser(), createParser());
		parser = new IssueReceiptActionParser(factory, receiverSelectorParsers,
				productSelectorParsers);
		try {
			element = XmlElement.fromText("<validXml />");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// Ignore, cannot occur here
			e.printStackTrace();
		}
	}

	public Selector<User> setReceiverParserReturnsCustomer() {
		setSomeParserCanParseReceiver();
		Selector<User> identitySelector = customer -> customer;
		receiverSelectorParsers.forEach(parser -> when(parser.parse(element))
				.thenReturn(identitySelector));
		return identitySelector;
	}

	public Selector<Product> setProductParserReturnsBoughtProduct() {
		setSomeParserCanParseProduct();
		Selector<Product> identitySelector = product -> product;
		productSelectorParsers.forEach(parser -> when(parser.parse(element))
				.thenReturn(identitySelector));
		return identitySelector;
	}

	public void setXml(String xml)
			throws ParserConfigurationException, SAXException, IOException {
		element = XmlElement.fromText(xml);
	}

	public void setNoParsersCanParseReceiver() {
		receiverSelectorParsers.forEach(
				parser -> when(parser.canParse(element)).thenReturn(false));
	}

	public void setNoParsersCanParseProduct() {
		productSelectorParsers.forEach(
				parser -> when(parser.canParse(element)).thenReturn(false));
	}

	public Parser<Selector<User>> setSomeParserCanParseReceiver() {
		Selector<User> identitySelector = customer -> customer;		
		Parser<Selector<User>> parser = receiverSelectorParsers.get(0);
		when(parser.canParse(element)).thenReturn(true);
		when(parser.parse(element)).thenReturn(identitySelector);
		return parser;
	}

	public Parser<Selector<Product>> setSomeParserCanParseProduct() {
		Selector<Product> identitySelector = product -> product;
		Parser<Selector<Product>> parser = productSelectorParsers.get(0);
		when(parser.canParse(element)).thenReturn(true);
		when(parser.parse(element)).thenReturn(identitySelector);
		return parser;
	}

	public void verifyCanParse() throws Exception {
		ParserAssert.canParse(getParser(), element);
	}

	public void verifyCannotParse() throws Exception {
		ParserAssert.cannotParse(getParser(), element);
	}

	public IssueReceiptActionParser getParser() {
		return parser;
	}
}
