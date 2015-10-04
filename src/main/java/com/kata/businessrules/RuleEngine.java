package com.kata.businessrules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.PhysicalProduct;
import com.kata.businessrules.products.Product;

public class RuleEngine {

	final Logger logger = LoggerFactory.getLogger(RuleEngine.class);

	private final ReceiptGenerator receiptGenerator;

	public RuleEngine(ReceiptGenerator receiptGenerator) {
		Preconditions.checkNotNull(receiptGenerator);
		this.receiptGenerator = receiptGenerator;
	}
	
	public void pay(User customer, User royaltyDepartment, Product product) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(royaltyDepartment);
		Preconditions.checkNotNull(product);
		logger.info("Customer paid for a product.");
		if(product instanceof PhysicalProduct) {
			receiptGenerator.generateReceipt();
			if(product instanceof Book) {
				receiptGenerator.generateReceipt();
			}
		}
	}	
}
