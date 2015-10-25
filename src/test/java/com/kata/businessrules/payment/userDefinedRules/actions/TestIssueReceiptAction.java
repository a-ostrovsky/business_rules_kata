package com.kata.businessrules.payment.userDefinedRules.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.DummyReceiptGenerator;
import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.ReceiptWithVisibleInternals;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;

public class TestIssueReceiptAction {

	private Action action;
	private ReceiptGenerator receiptGenerator;
	private Product boughtProduct;
	private Product productToReceiveReceiptFor;
	private User customer;
	private User receiverOfTheReceipt;

	private Receipt expectedReceipt(Product product) {
		return new ReceiptWithVisibleInternals(customer, product);
	}

	private void assertUserReceivedReceipt(User user, Product product) {
		verify(user).issueReceipt(expectedReceipt(product));
	}

	private <T> Selector<T> createSelectorWithFixedResult(T providedValue,
			T fixedResult) {
		@SuppressWarnings("unchecked")
		Selector<T> result = (Selector<T>) mock(Selector.class);
		when(result.select(providedValue)).thenReturn(fixedResult);
		return result;
	}

	@Before
	public void setup() {
		receiptGenerator = new DummyReceiptGenerator();

		customer = mock(User.class);
		receiverOfTheReceipt = mock(User.class);

		productToReceiveReceiptFor = ProductFixture.createArbitraryProduct();
		boughtProduct = ProductFixture.createArbitraryProduct();

		action = new IssueReceiptAction(receiptGenerator,
				createSelectorWithFixedResult(customer, receiverOfTheReceipt),
				createSelectorWithFixedResult(boughtProduct,
						productToReceiveReceiptFor));
	}

	@Test
	public void execute_receiptIsForCorrectProductToCorrectReceiver() {
		action.execute(customer, boughtProduct);
		assertUserReceivedReceipt(receiverOfTheReceipt,
				productToReceiveReceiptFor);
	}
}
