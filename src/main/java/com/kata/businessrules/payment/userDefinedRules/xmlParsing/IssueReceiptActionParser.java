package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Collection;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.IssueReceiptActionFactory;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class IssueReceiptActionParser implements Parser<Action> {

	private Collection<Parser<Selector<User>>> userSelectorParsers;
	private Collection<Parser<Selector<Product>>> productSelectorParsers;
	private IssueReceiptActionFactory factory;

	public IssueReceiptActionParser(IssueReceiptActionFactory factory,
			Collection<Parser<Selector<User>>> userSelectorParsers,
			Collection<Parser<Selector<Product>>> productSelectorParsers) {
		Preconditions.checkNotNull(userSelectorParsers);
		Preconditions.checkNotNull(productSelectorParsers);
		Preconditions.checkNotNull(factory);
		this.factory = factory;
		this.userSelectorParsers = userSelectorParsers;
		this.productSelectorParsers = productSelectorParsers;
	}

	@Override
	public Action parse(Element element) {
		Preconditions.checkNotNull(element);
		Parser<Selector<User>> userSelectorParser = userSelectorParsers.stream()
				.filter(parser -> parser.canParse(element)).findFirst().get();
		Selector<User> userSelector = userSelectorParser.parse(element);
		Parser<Selector<Product>> productSelectorParser = productSelectorParsers
				.stream().filter(parser -> parser.canParse(element)).findFirst()
				.get();
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
		boolean canParseReceiver = userSelectorParsers.stream()
				.anyMatch(parser -> parser.canParse(element));
		boolean canParseProduct = productSelectorParsers.stream()
				.anyMatch(parser -> parser.canParse(element));
		return isNameCorrect && canParseReceiver && canParseProduct;
	}

}
