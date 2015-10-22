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

public class TestTitleFilterIgnoringCaseParser {
	private TitleFilterIgnoringCaseParser parser;

	@Before
	public void setup() {
		parser = new TitleFilterIgnoringCaseParser();
	}
	
	@Test
	public void parse_title_filterForProductWithGivenType() throws Exception {
		Product applicableProduct = ProductFixture.createProduct("product title");
		Element element = XmlElement
				.fromText("<whenPaidFor productTitle=\"product title\"/>");
		Filter filter = parser.parse(element);
		assertThat(filter.isApplicable(applicableProduct), is(true));
	}

	@Test
	public void canParse_onlyTitleAttribute_true() throws Exception {
		ParserAssert.canParse(parser,
				"<whenPaidFor productTitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_noTitleAttribute_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<whenPaidFor productType=\"Membership\"/>");
	}

	@Test
	public void canParse_productTitleAttributeInCombinationWithAnotherAttribute_false()
			throws Exception {
		ParserAssert.cannotParse(parser,
				"<whenPaidFor productType=\"Video\" productTtitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_notWhenPaidForFilter_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<SOMETHING_ELSE productTitle=\"Video\"/>");
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, null);
	}
}
