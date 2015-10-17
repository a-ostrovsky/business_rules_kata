package com.kata.businessrules.payment.userDefinedRules.filters;

import com.google.common.base.Preconditions;
import com.kata.businessrules.products.Product;

public class ProductTypeFilter implements Filter {

	private Class<?> requiredClass;

	public ProductTypeFilter(Class<?> requiredClass) {
		Preconditions.checkNotNull(requiredClass);
		this.requiredClass = requiredClass;
	}

	@Override
	public boolean isApplicable(Product product) {
		return product != null
				&& requiredClass.isAssignableFrom(product.getClass());
	}

}
