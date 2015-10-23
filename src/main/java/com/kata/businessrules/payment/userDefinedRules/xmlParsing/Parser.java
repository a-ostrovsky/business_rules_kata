package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

public interface Parser<T> {
	T parse(Element element);

	boolean canParse(Element element);
}
