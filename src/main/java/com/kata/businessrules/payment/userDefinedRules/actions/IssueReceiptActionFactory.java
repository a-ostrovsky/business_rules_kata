package com.kata.businessrules.payment.userDefinedRules.actions;

import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public interface IssueReceiptActionFactory {
	IssueReceiptAction create(Selector<User> userSelector,
			Selector<Product> productSelector);
}
