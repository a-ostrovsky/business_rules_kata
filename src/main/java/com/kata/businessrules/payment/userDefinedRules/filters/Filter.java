package com.kata.businessrules.payment.userDefinedRules.filters;

import com.kata.businessrules.products.Product;

public interface Filter {
	boolean isApplicable(Product product);
}
