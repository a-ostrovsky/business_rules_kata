package com.kata.businessrules;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.products.Product;

import static org.mockito.Mockito.*;

public class TestRuleEngine {
	private RuleEngine engine;
	private ReceiptGenerator receiptGenerator;
	private CurrentUsers currentUsers;
	private Product physicalProduct;
	private Product book;	

	private void pay(Product product) {
		engine.pay(currentUsers, product);
	}

	private Receipt expectedReceipt(Product product) {
		return new ReceiptWithVisibleInternals(currentUsers.getCustomer(), product);
	}
	
	private void assertUserReceivedReceipt(User user, Product product){        
		verify(user).IssueReceipt(expectedReceipt(product));
	}

	@Before
	public void setup() {
		receiptGenerator = new DummyReceiptGenerator();
		currentUsers = new CurrentMockedUsers();
		engine = new RuleEngine(receiptGenerator);
		physicalProduct = ProductFixture.createSomePhysicalProduct();
		book = ProductFixture.createSomeBook();		
	}

	@Test
	public void pay_PhysicalProduct_receiptIsIssuedToCustomer() {
		pay(physicalProduct);
		assertUserReceivedReceipt(currentUsers.getCustomer(), physicalProduct);		
	}

	@Test
	public void pay_Book_receiptIsIssuedToCustomerAndToRoyaltyDepartment() {
		pay(book);
		assertUserReceivedReceipt(currentUsers.getCustomer(), book);
		assertUserReceivedReceipt(currentUsers.getRoyaltyDepartment(), book);
	}
	
	@Test
	public void pay_ArbitraryProduct_productIsAddedToUsersListOfBoughtProducts() {
		Product product = ProductFixture.createArbitraryProduct();
		pay(product);
		verify(currentUsers.getCustomer()).buy(product);
	}
}
