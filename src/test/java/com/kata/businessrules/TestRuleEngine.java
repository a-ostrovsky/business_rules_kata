package com.kata.businessrules;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.PhysicalProduct;
import com.kata.businessrules.products.Product;

import static org.mockito.Mockito.*;

public class TestRuleEngine {
	private RuleEngine engine;
	private ReceiptGenerator receiptGenerator;
	private User customer;
	private User royaltyDepartment;
	
	private void pay(Product product){
		engine.pay(customer, royaltyDepartment, product);
	}
	
	@Before
	public void setup(){
		receiptGenerator = new DummyReceiptGenerator();
		customer = mock(User.class);
		royaltyDepartment = mock(User.class);
		engine = new RuleEngine(receiptGenerator);
	}
	
	@Test
	public void pay_PhysicalProduct_receiptIsIssuedToCustomer(){
		pay(mock(PhysicalProduct.class));
		verify(customer).IssueReceipt(any(Receipt.class));
	}
	
	@Test
	public void pay_Book_receiptIsIssuedToCustomerAndToRoyaltyDepartment(){
		pay(mock(Book.class));
		verify(customer).IssueReceipt(any(Receipt.class));
		verify(royaltyDepartment).IssueReceipt(any(Receipt.class));
	}
}
