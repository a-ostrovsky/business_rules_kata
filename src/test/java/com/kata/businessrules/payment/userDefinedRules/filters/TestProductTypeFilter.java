package com.kata.businessrules.payment.userDefinedRules.filters;

import org.junit.Test;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.Product;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;

public class TestProductTypeFilter {
	
	private Product productThatIsNotFilteredOut;
	private Product productThatIsFilteredOut;
	private Filter filter;	

	@Before
	public void setup()
	{
		productThatIsNotFilteredOut = ProductFixture.createSomeBook();
		productThatIsFilteredOut = ProductFixture.createSomeMembership();
		filter = new ProductTypeFilter(Book.class);
	}
	
	@Test	
	public void isApplicable_ProductOfGivenType_true() {		
		assertThat(filter.isApplicable(productThatIsNotFilteredOut), is(true));
	}
	
	@Test	
	public void isApplicable_NotProductOfGivenType_false() {		
		assertThat(filter.isApplicable(productThatIsFilteredOut), is(false));
	}
	
	@Test	
	public void isApplicable_null_false() {		
		assertThat(filter.isApplicable(null), is(false));
	}
}
