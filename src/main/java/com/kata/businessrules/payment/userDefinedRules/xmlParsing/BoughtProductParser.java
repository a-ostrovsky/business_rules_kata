package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.kata.businessrules.products.Product;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class BoughtProductParser implements Parser<Selector<Product>>{

	@Override
	public Selector<Product> parse(Element element) {
		return product -> product;
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		String product = element.getAttribute("product");
		return "bought".equalsIgnoreCase(product);
	}

}
