package com.kata.businessrules;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TestRuleEngine {
	private RuleEngine engine;
	private ReceiptGenerator receiptGenerator;	

	@Before
	public void setup(){
		receiptGenerator = mock(ReceiptGenerator.class);
		engine = new RuleEngine(receiptGenerator);
	}
	
	@Test
	public void payForPhysicalProduct_receiptIsGenerated(){
		engine.payForPhysicalProduct();
		verify(receiptGenerator).generateReceipt();
	}
	
	@Test
	public void payForBook_twoReceiptsAreGenerated(){
		engine.payForBook();
		verify(receiptGenerator, times(2)).generateReceipt();
	}
}
