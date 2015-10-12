package com.kata.businessrules.payment;

import com.google.common.base.Preconditions;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.Product;

public class ReceiptForBookToRoyaltyDepartmentBehavior
		implements PaymentBehavior {

	private ReceiptGenerator receiptGenerator;
	private User royaltyDepartment;

	public ReceiptForBookToRoyaltyDepartmentBehavior(
			ReceiptGenerator receiptGenerator, User royaltyDepartment) {
		Preconditions.checkNotNull(receiptGenerator);
		Preconditions.checkNotNull(royaltyDepartment);
		this.receiptGenerator = receiptGenerator;
		this.royaltyDepartment = royaltyDepartment;
	}

	@Override
	public void pay(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		Receipt receipt = receiptGenerator.generateReceipt(customer, product);
		royaltyDepartment.issueReceipt(receipt);
		// receipt is issued to customer by PhysicalProductPaymentProcessor
	}

	@Override
	public boolean isApplicable(Product product) {
		return product instanceof Book;
	}

}
