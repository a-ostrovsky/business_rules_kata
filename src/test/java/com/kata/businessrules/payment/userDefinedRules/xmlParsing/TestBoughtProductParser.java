package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class TestBoughtProductParser {	
	private BoughtProductParser parser;

	@Before
	public void setup() {		
		parser = new BoughtProductParser();
	}

	@Test
	public void parse_validElement_identitySelector() throws Exception {
		Selector<Product> selector = parser.parse(XmlElement
				.fromText("<does_not_matter product=\"bought\" />"));
		Product product = mock(Product.class);
		assertThat(selector.select(product), is(product));
	}

	@Test
	public void canParse_boughtProduct_true() throws Exception {
		ParserAssert.canParse(parser,
				"<does_not_matter product=\"bought\" />");
	}

	@Test
	public void canParse_anotherProductThanBoughtOne_false()
			throws Exception {
		ParserAssert.cannotParse(parser,
				"<does_not_matter product=\"not_bought\" />");
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, (Element) null);
	}
}
