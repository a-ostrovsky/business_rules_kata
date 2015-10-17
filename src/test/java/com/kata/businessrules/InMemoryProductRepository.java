package com.kata.businessrules;

import java.util.HashMap;
import java.util.Map;

import com.kata.businessrules.products.Product;

public class InMemoryProductRepository implements ProductRepository {

	private final Map<String, Product> products = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Product> T getById(String id) {
		return (T) products.get(id);
	}

	public void addProduct(String id, Product product) {
		products.put(id, product);
	}
}
