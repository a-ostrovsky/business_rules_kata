package com.kata.businessrules.payment;

import com.google.common.base.Preconditions;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;
import com.kata.businessrules.products.Video;

public class FirstAidVideoAddingBehavior implements PaymentBehavior {

	private ReceiptGenerator receiptGenerator;

	public FirstAidVideoAddingBehavior(ReceiptGenerator receiptGenerator) {
		Preconditions.checkNotNull(receiptGenerator);
		this.receiptGenerator = receiptGenerator;
	}

	@Override
	public void pay(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		Video firstAid = new Video("First Aid");
		Receipt receipt = receiptGenerator.generateReceipt(customer, firstAid);
		customer.issueReceipt(receipt);
	}

	@Override
	public boolean isApplicable(Product product) {
		return product instanceof Video && ((Video) product).getTitle().trim()
				.equalsIgnoreCase("Learning to Ski");
	}
}
