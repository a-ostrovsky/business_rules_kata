package com.kata.businessrules.payment;

import com.google.common.base.Preconditions;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.PhysicalProduct;
import com.kata.businessrules.products.Product;

public class ReceiptForPhysicalProductToCustomerBehavior implements PaymentBehavior {

	private ReceiptGenerator receiptGenerator;

	public ReceiptForPhysicalProductToCustomerBehavior(ReceiptGenerator receiptGenerator) {
		Preconditions.checkNotNull(receiptGenerator);
		this.receiptGenerator = receiptGenerator;
	}
	
	@Override
	public void pay(User customer, Product product) {
		Preconditions.checkNotNull(customer);		
		Preconditions.checkNotNull(product);		
		Receipt receipt = receiptGenerator.generateReceipt(customer, product);
		customer.issueReceipt(receipt);
	}

	@Override
	public boolean isApplicable(Product product) {
		return product instanceof PhysicalProduct;
	}

}
