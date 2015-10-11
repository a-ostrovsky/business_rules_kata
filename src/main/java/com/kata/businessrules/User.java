package com.kata.businessrules;

import java.util.Collection;

import com.kata.businessrules.products.Product;

public interface User {
	void issueReceipt(Receipt receipt);
	
	void purchase(Product product);
	
	Collection<Product> getPurchasedProducts();
}
