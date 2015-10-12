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
import com.kata.businessrules.products.Product;

public class TestReceiptForPhysicalProductToCustomerBehavior {
	private PaymentBehavior behavior;
	private ReceiptGenerator receiptGenerator;
	private Product physicalProduct;
	private User customer;

	private void pay() {
		behavior.pay(customer, physicalProduct);
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
		behavior = new ReceiptForPhysicalProductToCustomerBehavior(
				receiptGenerator);
		physicalProduct = ProductFixture.createSomePhysicalProduct();
	}

	@Test
	public void pay_PhysicalProduct_receiptIsIssuedToCustomer() {
		pay();
		assertUserReceivedReceipt(customer, physicalProduct);
	}

	@Test
	public void isApplicable_onlyTrueForPhysicalProducts() {
		Product nonPhysicalProduct = mock(Product.class);
		assertThat("Must be applicable to physical product.",
				behavior.isApplicable(physicalProduct), is(true));
		assertThat("Must not be applicable to non physical product.",
				behavior.isApplicable(nonPhysicalProduct), is(false));
	}
}
