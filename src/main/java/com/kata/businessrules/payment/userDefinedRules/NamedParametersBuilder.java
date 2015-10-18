package com.kata.businessrules.payment.userDefinedRules;

import java.util.Map;

import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public interface NamedParametersBuilder {
	Map<String, Object> build(User customer, Product product);
}
