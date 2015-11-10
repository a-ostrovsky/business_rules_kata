package com.kata.businessrules;

import com.google.inject.AbstractModule;
import com.kata.businessrules.products.Product;

public class DummyMainModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ReceiptGenerator.class).toInstance(new ReceiptGenerator() {
			@Override
			public Receipt generateReceipt(User customer, Product product) {
				return null;
			}
		});
		bind(UserRepository.class).toInstance(new UserRepository() {
			@Override
			public User getById(String id) {
				return null;
			}
		});
		bind(ProductRepository.class).toInstance(new ProductRepository() {
			@Override
			public <T extends Product> T getById(String id) {
				return null;
			}
		});
	}

}
