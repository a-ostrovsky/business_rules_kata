package com.kata.businessrules;

import com.kata.businessrules.products.Product;

public interface User {
	void IssueReceipt(Receipt receipt);
	
	void buy(Product product);
}
