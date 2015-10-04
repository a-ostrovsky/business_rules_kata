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
	private Product physicalProduct;
	private Product book;
	
	private void pay(Product product){
		engine.pay(customer, royaltyDepartment, product);
	}
	
	private Receipt expectedReceipt(Product product){
		return new ReceiptWithVisibleInternals(customer, product);
	}
	
	@Before
	public void setup(){
		receiptGenerator = new DummyReceiptGenerator();
		customer = mock(User.class);
		royaltyDepartment = mock(User.class);
		engine = new RuleEngine(receiptGenerator);
		physicalProduct = mock(PhysicalProduct.class);
		book = mock(Book.class);
	}
	
	@Test
	public void pay_PhysicalProduct_receiptIsIssuedToCustomer(){
		pay(physicalProduct);		
		verify(customer).IssueReceipt(expectedReceipt(physicalProduct));
	}
	
	@Test
	public void pay_Book_receiptIsIssuedToCustomerAndToRoyaltyDepartment(){
		pay(book);
		verify(customer).IssueReceipt(expectedReceipt(book));
		verify(royaltyDepartment).IssueReceipt(expectedReceipt(book));
	}
}
