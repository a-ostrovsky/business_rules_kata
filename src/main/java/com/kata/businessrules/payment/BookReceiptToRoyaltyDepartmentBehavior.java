package com.kata.businessrules.payment;

import com.google.common.base.Preconditions;
import com.kata.businessrules.CurrentUsers;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.Product;

public class BookReceiptToRoyaltyDepartmentBehavior implements PaymentBehavior {

	private ReceiptGenerator receiptGenerator;

	public BookReceiptToRoyaltyDepartmentBehavior(ReceiptGenerator receiptGenerator) {
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
