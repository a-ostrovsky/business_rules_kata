package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.kata.businessrules.User;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class IssueReceiptActionParser implements Parser<Action> {

	private Parser<Selector<User>> userSelectorParser;
	private Parser<Selector<Product>> productSelectorParser;
	private IssueReceiptActionFactory factory;

	@Inject
	public IssueReceiptActionParser(IssueReceiptActionFactory factory,
			Parser<Selector<User>> userSelectorParser,
			Parser<Selector<Product>> productSelectorParser) {
		Preconditions.checkNotNull(userSelectorParser);
		Preconditions.checkNotNull(productSelectorParser);
		Preconditions.checkNotNull(factory);
		this.factory = factory;
		this.userSelectorParser = userSelectorParser;
		this.productSelectorParser = productSelectorParser;
	}

	@Override
	public Action parse(Element element) {
		Preconditions.checkNotNull(element);
		Selector<User> userSelector = userSelectorParser.parse(element);
		Selector<Product> productSelector = productSelectorParser
				.parse(element);
		return factory.create(userSelector, productSelector);
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		boolean isNameCorrect = "receiptFor"
				.equalsIgnoreCase(element.getTagName());
		boolean canParseReceiver = userSelectorParser.canParse(element);
		boolean canParseProduct = productSelectorParser.canParse(element);
		return isNameCorrect && canParseReceiver && canParseProduct;
	}

}
