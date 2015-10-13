package com.kata.businessrules;

import java.util.ArrayList;
import java.util.Collection;

import com.kata.businessrules.products.Product;

public class CustomerFixture {
	public static User createCustomer() {
		User user = new User() {

			private final Collection<Product> purchasedProducts = new ArrayList<>();

			@Override
			public void issueReceipt(Receipt receipt) {
				// do nothing
			}

			@Override
			public void purchase(Product product) {
				purchasedProducts.add(product);
			}

			@Override
			public Collection<Product> getPurchasedProducts() {
				return purchasedProducts;
			}
		};
		return user;
	}
}
