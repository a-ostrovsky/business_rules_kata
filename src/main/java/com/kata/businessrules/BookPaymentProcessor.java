package com.kata.businessrules;

import com.google.common.base.Preconditions;
import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.Product;

public class BookPaymentProcessor implements PaymentProcessor {

	private ReceiptGenerator receiptGenerator;

	public BookPaymentProcessor(ReceiptGenerator receiptGenerator) {
		Preconditions.checkNotNull(receiptGenerator);
		this.receiptGenerator = receiptGenerator;
	}
	
	@Override
	public void pay(CurrentUsers users, Product product) {
		Preconditions.checkNotNull(users);		
		Preconditions.checkNotNull(product);
		User customer = users.getCustomer();
		User royaltyDepartment = users.getRoyaltyDepartment();
		Receipt receipt = receiptGenerator.generateReceipt(customer, product);
		royaltyDepartment.issueReceipt(receipt);
		//receipt is issued to customer by PhysicalProductPaymentProcessor
	}

	@Override
	public boolean canProcess(Product product) {
		return product instanceof Book;
	}

}
