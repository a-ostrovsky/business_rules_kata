package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Collection;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kata.businessrules.User;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class IssueReceiptActionParser implements Parser<Action> {

	private Collection<Parser<Selector<User>>> receiverSelectorParsers;
	private Collection<Parser<Selector<Product>>> productSelectorParsers;

	public IssueReceiptActionParser(
			Collection<Parser<Selector<User>>> receiverSelectorParsers,
			Collection<Parser<Selector<Product>>> productSelectorParsers) {
		Preconditions.checkNotNull(receiverSelectorParsers);
		Preconditions.checkNotNull(productSelectorParsers);
		this.receiverSelectorParsers = receiverSelectorParsers;
		this.productSelectorParsers = productSelectorParsers;
	}

	@Override
	public Action parse(Element element) {
		Preconditions.checkNotNull(element);
		return null;
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		boolean isNameCorrect = "receiptFor"
				.equalsIgnoreCase(element.getTagName());
		boolean canParseReceiver = receiverSelectorParsers.stream()
				.anyMatch(parser -> parser.canParse(element));
		boolean canParseProduct = productSelectorParsers.stream()
				.anyMatch(parser -> parser.canParse(element));
		return isNameCorrect && canParseReceiver && canParseProduct;
	}

}
