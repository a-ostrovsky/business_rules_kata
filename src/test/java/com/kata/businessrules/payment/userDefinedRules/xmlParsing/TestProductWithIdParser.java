package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import com.kata.businessrules.InMemoryProductRepository;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class TestProductWithIdParser {
	private static final String PRODUCT_ID = "firstAid";
	private ProductWithIdParser parser;
	private InMemoryProductRepository productRepository;
	private Product productToIssueReceiptFor;

	@Before
	public void setup() {
		productToIssueReceiptFor = mock(Product.class);
		productRepository = new InMemoryProductRepository();
		productRepository.addProduct(PRODUCT_ID, productToIssueReceiptFor);
		parser = new ProductWithIdParser(productRepository);
	}

	@Test
	public void parse_validElement_selectorForProductWithGivenId()
			throws Exception {
		Selector<Product> selector = parser.parse(XmlElement.fromText(String
				.format("<does_not_matter productId=\"%s\" />", PRODUCT_ID)));
		Product boughtProduct = mock(Product.class);
		assertThat(selector.select(boughtProduct),
				is(productToIssueReceiptFor));
	}

	@Test
	public void canParse_productWithId_true() throws Exception {
		ParserAssert.canParse(parser,
				"<does_not_matter productId=\"firstAid\" />");
	}

	@Test
	public void canParse_productWithIdIsNotGiven_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<does_not_matter product=\"bought\" />");
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, (Element) null);
	}
}
