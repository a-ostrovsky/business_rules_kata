package com.kata.businessrules;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.products.Product;

public class TestBookPaymentProcessor {
	private PaymentProcessor processor;
	private ReceiptGenerator receiptGenerator;
	private CurrentUsers currentUsers;
	private Product book;

	private void pay() {
		processor.pay(currentUsers, book);
	}

	private Receipt expectedReceipt(Product product) {
		return new ReceiptWithVisibleInternals(currentUsers.getCustomer(),
				product);
	}

	private void assertUserReceivedReceipt(User user, Product product) {
		verify(user).issueReceipt(expectedReceipt(product));
	}

	@Before
	public void setup() {
		receiptGenerator = new DummyReceiptGenerator();
		currentUsers = new CurrentMockedUsers();
		processor = new BookPaymentProcessor(receiptGenerator);		
		book = ProductFixture.createSomeBook();
	}
	
	@Test
	public void pay_Book_receiptIsIssuedToRoyaltyDepartment() {
		pay();
		assertUserReceivedReceipt(currentUsers.getRoyaltyDepartment(), book);
	}	
}
