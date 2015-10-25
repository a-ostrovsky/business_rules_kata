package com.kata.businessrules.payment.userDefinedRules.actions;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.UserRepository;
import com.kata.businessrules.products.Product;

public class ReceiptToRoyaltyDepartmentAction implements Action {

	private ReceiptGenerator receiptGenerator;
	private UserRepository userRepository;

	public ReceiptToRoyaltyDepartmentAction(ReceiptGenerator receiptGenerator,
			UserRepository userRepository) {
		Preconditions.checkNotNull(receiptGenerator);
		Preconditions.checkNotNull(userRepository);
		this.receiptGenerator = receiptGenerator;
		this.userRepository = userRepository;
	}

	@Override
	public void execute(User customer, Product product,
			Map<String, Object> namedParameters) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		Receipt receipt = receiptGenerator.generateReceipt(customer, product);
		User royaltyDepartment = userRepository.getById("royaltyDepartment");
		royaltyDepartment.issueReceipt(receipt);
	}

}
