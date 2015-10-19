package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.kata.businessrules.payment.userDefinedRules.filters.Filter;

public interface FilterParser {
	Filter parse(Element element);

	boolean canParse(Element element);
}
