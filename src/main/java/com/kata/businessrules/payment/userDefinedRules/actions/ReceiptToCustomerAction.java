package com.kata.businessrules.payment.userDefinedRules.actions;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public class ReceiptToCustomerAction implements Action {

	private ReceiptGenerator receiptGenerator;

	public ReceiptToCustomerAction(ReceiptGenerator receiptGenerator) {
		Preconditions.checkNotNull(receiptGenerator);
		this.receiptGenerator = receiptGenerator;
	}

	@Override
	public void execute(User customer, Product product,
			Map<String, Object> namedParameters) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		Receipt receipt = receiptGenerator.generateReceipt(customer, product);
		customer.issueReceipt(receipt);
	}

}
