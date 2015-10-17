package com.kata.businessrules.payment.userDefinedRules.filters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kata.businessrules.products.Product;
import com.kata.businessrules.products.Video;

public class TitleFilterIgnoringCase implements Filter {

	private final Logger logger = LoggerFactory
			.getLogger(TitleFilterIgnoringCase.class);

	private String expectedTitle;

	public TitleFilterIgnoringCase(String expectedTitle) {
		this.expectedTitle = expectedTitle;
	}

	@Override
	public boolean isApplicable(Product product) {
		if (product == null) {
			return false;
		}
		Optional<Method> getTitleMethod = Arrays
				.stream(product.getClass().getMethods())
				.filter(method -> method.getName().equals("getTitle"))
				.findFirst();
		if (!getTitleMethod.isPresent()) {
			return false;
		}
		try {
			String title = (String) getTitleMethod.get().invoke(product);
			return title.equalsIgnoreCase(expectedTitle);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("Could not get title of product.", e);
			return false;
		}
	}

}
