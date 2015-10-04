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
		receiptGenerator = mock(ReceiptGenerator.class);
		customer = mock(User.class);
		royaltyDepartment = mock(User.class);
		engine = new RuleEngine(receiptGenerator);
	}
	
	@Test
	public void pay_PhysicalProduct_receiptIsGenerated(){
		pay(mock(PhysicalProduct.class));
		verify(receiptGenerator).generateReceipt();
	}
	
	@Test
	public void pay_Book_twoReceiptsAreGenerated(){
		pay(mock(Book.class));
		verify(receiptGenerator, times(2)).generateReceipt();
	}
}
