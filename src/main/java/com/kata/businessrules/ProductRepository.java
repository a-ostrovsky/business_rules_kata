package com.kata.businessrules;

import com.kata.businessrules.products.Product;

public interface ProductRepository {
	<T extends Product> T getById(String id);
}
