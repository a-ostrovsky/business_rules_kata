package com.kata.businessrules;

import com.kata.businessrules.payment.PaymentProcessor;
import com.kata.businessrules.products.Product;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

public class PaymentProcessorFixture {

	public static PaymentProcessor thatCanProcess(Product product) {
		PaymentProcessor result = mock(PaymentProcessor.class);
		when(result.canProcess(product)).thenReturn(true);
		when(result.canProcess(not(eq(product)))).thenReturn(false);
		return result;
	}
	
	public static PaymentProcessor thatCanNotProcess(Product product) {
		PaymentProcessor result = mock(PaymentProcessor.class);
		when(result.canProcess(product)).thenReturn(false);		
		return result;
	}

}
