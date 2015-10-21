package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.w3c.dom.Element;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;
import com.kata.businessrules.products.Product;

public class TestProductTypeFilterParser {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	private ProductTypeFilterParser parser;

	private void verifyCanParse(boolean expectedCanParse, String xmlElement)
			throws Exception {
		assertThat(parser.canParse(XmlElement.fromText(xmlElement)),
				is(expectedCanParse));
	}

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
		verifyCanParse(true, "<whenPaidFor productType=\"Membership\"/>");
	}
	
	@Test
	public void canParse_productTypeThatDoesNotExist_false() throws Exception {
		verifyCanParse(false, "<whenPaidFor productType=\"ProductTypeThatDoesNotExist\"/>");
	}

	@Test
	public void canParse_noProductTypeAttribute_false() throws Exception {
		verifyCanParse(false,
				"<whenPaidFor productTtitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_productTypeAttributeInCombinationWithAnotherAttribute_false()
			throws Exception {
		verifyCanParse(false,
				"<whenPaidFor productType=\"Video\" productTtitle=\"Learning to Ski\"/>");
	}

	@Test
	public void canParse_notWhenPaidForFilter_false() throws Exception {
		verifyCanParse(false, "<SOMETHING_ELSE productType=\"Video\"/>");
	}
}
