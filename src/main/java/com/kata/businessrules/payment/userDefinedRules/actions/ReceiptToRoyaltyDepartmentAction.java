package com.kata.businessrules.payment.userDefinedRules.actions;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public class ReceiptToRoyaltyDepartmentAction implements Action {

	private ReceiptGenerator receiptGenerator;
	private User royaltyDepartment;

	public ReceiptToRoyaltyDepartmentAction(ReceiptGenerator receiptGenerator,
			User royaltyDepartment) {
		Preconditions.checkNotNull(receiptGenerator);
		Preconditions.checkNotNull(royaltyDepartment);
		this.receiptGenerator = receiptGenerator;
		this.royaltyDepartment = royaltyDepartment;
	}

	@Override
	public void execute(User customer, Product product,
			Map<String, Object> namedParameters) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		Receipt receipt = receiptGenerator.generateReceipt(customer, product);
		royaltyDepartment.issueReceipt(receipt);
	}

}
