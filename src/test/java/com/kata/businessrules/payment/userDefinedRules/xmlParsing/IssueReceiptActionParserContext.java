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
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class IssueReceiptActionParserContext {

	private IssueReceiptActionParser parser;
	private List<Parser<Selector<User>>> receiverSelectorParsers;
	private List<Parser<Selector<Product>>> productSelectorParsers;
	private Element element;	

	@SuppressWarnings("unchecked")
	private <T> Parser<Selector<T>> createParser() {
		return (Parser<Selector<T>>) mock(Parser.class);
	}

	public IssueReceiptActionParserContext() {
		receiverSelectorParsers = Arrays.asList(createParser(), createParser());
		productSelectorParsers = Arrays.asList(createParser(), createParser());
		parser = new IssueReceiptActionParser(receiverSelectorParsers,
				productSelectorParsers);
		try {
			element = XmlElement.fromText("<validXml />");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// Ignore, cannot occur here
			e.printStackTrace();
		}
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

	public void setSomeParserCanParseReceiver() {
		Parser<Selector<User>> parser = receiverSelectorParsers.get(0);
		when(parser.canParse(element)).thenReturn(true);
	}

	public void setSomeParserCanParseProduct() {
		Parser<Selector<Product>> parser = productSelectorParsers.get(0);
		when(parser.canParse(element)).thenReturn(true);
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
