package com.kata.businessrules;

import static org.mockito.Mockito.mock;

import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.Membership;
import com.kata.businessrules.products.PhysicalProduct;
import com.kata.businessrules.products.Product;

public class ProductFixture {
	public static Product createSomePhysicalProduct() {
		return mock(PhysicalProduct.class);
	}

	public static Product createSomeBook() {
		return mock(Book.class);
	}
	
	public static Product createSomeMembership() {
		return mock(Membership.class);
	}
	
	public static Product createArbitraryProduct() {
		return mock(Product.class);
	}
}
