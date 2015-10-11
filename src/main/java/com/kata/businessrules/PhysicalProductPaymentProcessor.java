package com.kata.businessrules;

import com.google.common.base.Preconditions;
import com.kata.businessrules.products.PhysicalProduct;
import com.kata.businessrules.products.Product;

public class PhysicalProductPaymentProcessor implements PaymentProcessor {

	private ReceiptGenerator receiptGenerator;

	public PhysicalProductPaymentProcessor(ReceiptGenerator receiptGenerator) {
		Preconditions.checkNotNull(receiptGenerator);
		this.receiptGenerator = receiptGenerator;
	}
	
	@Override
	public void pay(CurrentUsers users, Product product) {
		Preconditions.checkNotNull(users);		
		Preconditions.checkNotNull(product);
		User customer = users.getCustomer();
		Receipt receipt = receiptGenerator.generateReceipt(customer, product);
		customer.issueReceipt(receipt);
	}

	@Override
	public boolean canProcess(Product product) {
		return product instanceof PhysicalProduct;
	}

}
