package com.kata.businessrules.payment.userDefinedRules.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.DummyReceiptGenerator;
import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.ReceiptWithVisibleInternals;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public class TestReceiptToCustomerAction {
	
	private Action action;
	private ReceiptGenerator receiptGenerator;
	private Product product;
	private User customer;

	private Receipt expectedReceipt(Product product) {
		return new ReceiptWithVisibleInternals(customer, product);
	}

	private void assertUserReceivedReceipt(User user, Product product) {
		verify(user).issueReceipt(expectedReceipt(product));
	}

	@Before
	public void setup() {
		receiptGenerator = new DummyReceiptGenerator();
		customer = mock(User.class);
		action = new ReceiptToCustomerAction(receiptGenerator);
		product = ProductFixture.createArbitraryProduct();
	}

	@Test
	public void execute_receiptIsIssuedToCustomer() {
		action.execute(customer, product, null);
		assertUserReceivedReceipt(customer, product);
	}
}
