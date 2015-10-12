package com.kata.businessrules.payment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
import com.kata.businessrules.payment.ReceiptForBookToRoyaltyDepartmentBehavior;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.products.Product;

public class TestReceiptForBookToRoyaltyDepartmentBehavior {
	private PaymentBehavior behavior;
	private ReceiptGenerator receiptGenerator;
	private Product book;
	private User customer;
	private User royaltyDepartment;

	private void pay() {
		behavior.pay(customer, book);
	}

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
		royaltyDepartment = mock(User.class);
		behavior = new ReceiptForBookToRoyaltyDepartmentBehavior(
				receiptGenerator, royaltyDepartment);
		book = ProductFixture.createSomeBook();
	}

	@Test
	public void pay_Book_receiptIsIssuedToRoyaltyDepartment() {
		pay();
		assertUserReceivedReceipt(royaltyDepartment, book);
	}

	@Test
	public void isApplicable_onlyTrueForPhysicalProducts() {
		Product nonBook = mock(Product.class);
		assertThat("Must be applicable to book.", behavior.isApplicable(book),
				is(true));
		assertThat("Must not be applicable to non book.",
				behavior.isApplicable(nonBook), is(false));
	}
}
