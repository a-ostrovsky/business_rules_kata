package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.google.common.base.Strings;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;

public class ProductTypeFilterParser implements FilterParser {

	@Override
	public Filter parse(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canParse(Element element) {
		boolean hasProductTypeAttribute = !Strings
				.isNullOrEmpty(element.getAttribute("productType"));
		boolean hasOtherAttributes = element.getAttributes().getLength() != 1;
		return hasProductTypeAttribute && !hasOtherAttributes;
	}

}
