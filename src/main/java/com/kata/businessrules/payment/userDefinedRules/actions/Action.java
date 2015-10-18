package com.kata.businessrules.payment.userDefinedRules.actions;

import java.util.Map;

import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public interface Action {
	void execute(User customer, Product product, Map<String, Object> namedParameters);
}
