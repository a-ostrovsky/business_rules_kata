package com.kata.businessrules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kata.businessrules.products.Book;
import com.kata.businessrules.products.Membership;
import com.kata.businessrules.products.PhysicalProduct;
import com.kata.businessrules.products.Product;
import com.kata.businessrules.products.UpgradedMembership;

import com.kata.businessrules.products.Video;

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
	
	public static Product createSomeUpgradedMembership() {
		return mock(UpgradedMembership.class);
	}
	
	public static Product createArbitraryProduct() {
		return mock(Product.class);
	}
	
	public static Product createProduct(String title) {
		Video product = mock(Video.class);
		when(product.getTitle()).thenReturn(title);
		return product;
	}
}
