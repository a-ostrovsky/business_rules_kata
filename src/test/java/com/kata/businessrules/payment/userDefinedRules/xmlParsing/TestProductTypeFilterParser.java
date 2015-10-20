package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.XmlElement;

public class TestProductTypeFilterParser {

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
	public void canParse_onlyProductTypeAttribute_true() throws Exception {
		verifyCanParse(true, "<whenPaidFor productType=\"Membership\"/>");
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
