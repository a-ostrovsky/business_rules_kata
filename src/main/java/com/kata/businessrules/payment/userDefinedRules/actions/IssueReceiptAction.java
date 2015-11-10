package com.kata.businessrules.payment.userDefinedRules.actions;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public class IssueReceiptAction implements Action {

	private ReceiptGenerator receiptGenerator;
	private Selector<User> userSelector;
	private Selector<Product> productSelector;

	@Inject
	public IssueReceiptAction(ReceiptGenerator receiptGenerator,
			@Assisted Selector<User> userSelector,
			@Assisted Selector<Product> productSelector) {
		Preconditions.checkNotNull(receiptGenerator);
		Preconditions.checkNotNull(userSelector);
		Preconditions.checkNotNull(productSelector);
		this.userSelector = userSelector;
		this.productSelector = productSelector;
		this.receiptGenerator = receiptGenerator;
	}

	@Override
	public void execute(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		Product productToIssueReceiptFor = productSelector.select(product);
		User receiverOfTheReceipt = userSelector.select(customer);
		Receipt receipt = receiptGenerator.generateReceipt(customer,
				productToIssueReceiptFor);
		receiverOfTheReceipt.issueReceipt(receipt);
	}

}
