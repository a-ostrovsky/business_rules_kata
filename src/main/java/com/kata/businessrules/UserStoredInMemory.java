package com.kata.businessrules;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.kata.businessrules.products.Product;

public class UserStoredInMemory implements User {

	private final Logger logger = LoggerFactory.getLogger(UserStoredInMemory.class);
	
	private final Collection<Product> purchasedProducts = new ArrayList<>();
	
	@Override
	public void issueReceipt(Receipt receipt) {
		logger.info("Got receipt.");		
	}

	@Override
	public void purchase(Product product) {
		Preconditions.checkNotNull(product);
		purchasedProducts.add(product);
	}

	@Override
	public Collection<Product> getPurchasedProducts() {
		return ImmutableList.copyOf(purchasedProducts);
	}

}
