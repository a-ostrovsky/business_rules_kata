package com.kata.businessrules.payment;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.CurrentMockedUsers;
import com.kata.businessrules.CurrentUsers;
import com.kata.businessrules.DummyReceiptGenerator;
import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.ReceiptWithVisibleInternals;
import com.kata.businessrules.User;
import com.kata.businessrules.payment.PaymentProcessor;
import com.kata.businessrules.payment.PhysicalProductPaymentProcessor;
import com.kata.businessrules.products.Product;

import static org.mockito.Mockito.*;

public class TestPhysicalProductPaymentProcessor {
	private PaymentProcessor processor;
	private ReceiptGenerator receiptGenerator;
	private CurrentUsers currentUsers;
	private Product physicalProduct;

	private void pay() {
		processor.pay(currentUsers, physicalProduct);
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
		processor = new PhysicalProductPaymentProcessor(receiptGenerator);
		physicalProduct = ProductFixture.createSomePhysicalProduct();
	}

	@Test
	public void pay_PhysicalProduct_receiptIsIssuedToCustomer() {
		pay();
		assertUserReceivedReceipt(currentUsers.getCustomer(), physicalProduct);
	}	
}
