package com.kata.businessrules;

import com.kata.businessrules.products.Product;

public class DummyReceiptGenerator implements ReceiptGenerator {

	@Override
	public Receipt generateReceipt(User customer, Product product) {
		return new ReceiptWithVisibleInternals(customer, product);
	}	

}
