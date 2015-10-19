package com.kata.businessrules.payment.userDefinedRules;

import java.util.HashMap;
import java.util.Map;

import com.kata.businessrules.User;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.products.Product;

public class DummyMessageParametersBuilder implements NamedParametersBuilder {

	@Override
	public Map<String, Object> build(User customer, Product product) {
		Message message = new Message() {};
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		return result;
	}

}
