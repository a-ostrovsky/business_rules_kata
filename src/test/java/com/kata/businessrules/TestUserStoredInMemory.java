package com.kata.businessrules;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;

import com.kata.businessrules.products.Product;

public class TestUserStoredInMemory {
	
	private User user;

	@Before
	public void setup(){
		user = new UserStoredInMemory();
	}
	
	@Test
	public void purchase_thengetPurchasedProducts_productIsReturned(){
		Product firstProduct = ProductFixture.createArbitraryProduct();
		Product secondProduct = ProductFixture.createArbitraryProduct();
		
		user.purchase(firstProduct);
		user.purchase(secondProduct);
		
		Collection<Product> purchasedProducts = user.getPurchasedProducts();
		assertThat(purchasedProducts, hasItems(firstProduct, secondProduct));
	}
}
