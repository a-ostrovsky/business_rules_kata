package com.kata.businessrules.payment;

import com.kata.businessrules.CurrentUsers;
import com.kata.businessrules.products.Product;

public interface PaymentBehavior {
	void pay(CurrentUsers users, Product product);
	boolean canProcess(Product product);
}
