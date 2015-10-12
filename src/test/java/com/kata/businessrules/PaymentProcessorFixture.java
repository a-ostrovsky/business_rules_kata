package com.kata.businessrules;

import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.products.Product;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

public class PaymentProcessorFixture {

	public static PaymentBehavior thatCanProcess(Product product) {
		PaymentBehavior result = mock(PaymentBehavior.class);
		when(result.isApplicable(product)).thenReturn(true);
		when(result.isApplicable(not(eq(product)))).thenReturn(false);
		return result;
	}
	
	public static PaymentBehavior thatCanNotProcess(Product product) {
		PaymentBehavior result = mock(PaymentBehavior.class);
		when(result.isApplicable(product)).thenReturn(false);		
		return result;
	}

}
