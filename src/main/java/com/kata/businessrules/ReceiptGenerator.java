package com.kata.businessrules;

import com.kata.businessrules.products.Product;

public interface ReceiptGenerator {
	Receipt generateReceipt(User customer, Product product);
}
