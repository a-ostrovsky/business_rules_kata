package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.google.common.base.Strings;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;

public class TitleFilterIgnoringCaseParser implements FilterParser {

	@Override
	public Filter parse(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		boolean isNameCorrect = "whenPaidFor"
				.equalsIgnoreCase(element.getTagName());
		boolean isProductTitle = !Strings
				.isNullOrEmpty(element.getAttribute("productTitle"));
		boolean hasOtherAttributes = element.getAttributes().getLength() != 1;
		return isNameCorrect && isProductTitle && !hasOtherAttributes;
	}

}
