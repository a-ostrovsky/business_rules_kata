package com.kata.businessrules;

import com.kata.businessrules.products.Product;

public interface PaymentProcessor {
	void pay(CurrentUsers users, Product product);
	boolean canProcess(Product product);
}
