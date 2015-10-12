package com.kata.businessrules.payment;

import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public interface PaymentBehavior {
	void pay(User customer, Product product);
	boolean isApplicable(Product product);
}
