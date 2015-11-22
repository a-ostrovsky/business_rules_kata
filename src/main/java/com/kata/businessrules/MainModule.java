package com.kata.businessrules;

import org.w3c.dom.Document;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.kata.businessrules.products.Product;

public class MainModule extends AbstractModule {

	@Override
	protected void configure() {
		installDummyModules();
		bind(new TypeLiteral<RuleEngineLoader<Document>>() {
		});
	}

	private void installDummyModules() {
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
