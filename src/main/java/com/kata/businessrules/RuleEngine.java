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
	
	public void pay(CurrentUsers users, Product product) {
		Preconditions.checkNotNull(users);		
		Preconditions.checkNotNull(product);
		users.getCustomer().buy(product);
		User customer = users.getCustomer();
		User royaltyDepartment = users.getRoyaltyDepartment();
		logger.info("Customer paid for a product.");
		if(product instanceof PhysicalProduct) {
			Receipt receipt = receiptGenerator.generateReceipt(customer, product);
			customer.IssueReceipt(receipt);
			if(product instanceof Book) {
				royaltyDepartment.IssueReceipt(receipt);
			}
		}
	}	
}
