package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Optional;
import java.util.Set;

import org.reflections.Reflections;
import org.w3c.dom.Element;

import com.kata.businessrules.payment.userDefinedRules.filters.Filter;
import com.kata.businessrules.payment.userDefinedRules.filters.ProductTypeFilter;
import com.kata.businessrules.products.Product;

public class ProductTypeFilterParser implements FilterParser {

	private Optional<Class<? extends Product>> getProductType(
			String productTypeName) {
		Reflections reflections = new Reflections("com.kata.businessrules");
		Set<Class<? extends Product>> subTypes = reflections
				.getSubTypesOf(Product.class);
		Optional<Class<? extends Product>> productType = subTypes.stream()
				.filter(productClass -> productClass.getSimpleName()
						.equals(productTypeName))
				.findFirst();
		return productType;
	}

	@Override
	public Filter parse(Element element) {
		String productTypeName = element.getAttribute("productType");		
		Optional<Class<? extends Product>> productType = getProductType(productTypeName);		
		return new ProductTypeFilter(productType.get());

	}

	@Override
	public boolean canParse(Element element) {
		boolean isNameCorrect = "whenPaidFor"
				.equalsIgnoreCase(element.getTagName());
		String productTypeName = element.getAttribute("productType");
		boolean isProductTypeCorrect = getProductType(productTypeName).isPresent();
		boolean hasOtherAttributes = element.getAttributes().getLength() != 1;
		return isNameCorrect && isProductTypeCorrect && !hasOtherAttributes;
	}

}
