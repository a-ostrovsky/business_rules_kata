package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kata.businessrules.ProductRepository;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.products.Product;

public class ProductWithIdParser implements Parser<Selector<Product>> {

	private ProductRepository productRepository;

	public ProductWithIdParser(ProductRepository productRepository) {
		Preconditions.checkNotNull(productRepository);
		this.productRepository = productRepository;

	}

	@Override
	public Selector<Product> parse(Element element) {
		Preconditions.checkNotNull(element);
		String productId = element.getAttribute("productId");
		return product -> productRepository.getById(productId);
	}

	@Override
	public boolean canParse(Element element) {
		if (element == null) {
			return false;
		}
		boolean hasProductId = !Strings
				.isNullOrEmpty(element.getAttribute("productId"));
		return hasProductId;
	}

}
