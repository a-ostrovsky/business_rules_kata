package com.kata.businessrules;

import com.kata.businessrules.products.Product;

public interface RuleEngine {

	void pay(User customer, Product product);

}