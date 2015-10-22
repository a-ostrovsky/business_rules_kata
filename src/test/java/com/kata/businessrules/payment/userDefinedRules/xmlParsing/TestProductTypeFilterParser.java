package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;
import com.kata.businessrules.products.Product;

public class TestProductTypeFilterParser {

	private ProductTypeFilterParser parser;
	
	@Before
	public void setup() {
		parser = new ProductTypeFilterParser();
	}

	@Test
	public void parse_validType_productOfGivenType() throws Exception {
		Product applicableProduct = ProductFixture.createSomeMembership();
		Element element = XmlElement
				.fromText("<whenPaidFor productType=\"Membership\"/>");
		Filter filter = parser.parse(element);
		assertThat(filter.isApplicable(applicableProduct), is(true));
	}

	@Test
	public void canParse_onlyProductTypeAttribute_true() throws Exception {
		ParserAssert.canParse(parser,
				"<whenPaidFor productType=\"Membership\"/>");
	}

	@Test
	public void canParse_productTypeThatDoesNotExist_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<whenPaidFor productType=\"ProductTypeThatDoesNotExist\"/>");
	}

	@Test
	public void canParse_noProductTypeAttribute_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<whenPaidFor productTtitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_productTypeAttributeInCombinationWithAnotherAttribute_false()
			throws Exception {
		ParserAssert.cannotParse(parser,
				"<whenPaidFor productType=\"Video\" productTtitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_notWhenPaidForFilter_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<SOMETHING_ELSE productType=\"Video\"/>");
	}
	
	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, null);
	}
}
