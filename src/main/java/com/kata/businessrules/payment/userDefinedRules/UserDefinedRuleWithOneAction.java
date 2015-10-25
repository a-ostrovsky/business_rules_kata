package com.kata.businessrules.payment.userDefinedRules;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;
import com.kata.businessrules.products.Product;

public class UserDefinedRuleWithOneAction implements PaymentBehavior {

	private Action action;
	private Filter filter;
	private NamedParametersBuilder namedParametersBuilder;

	public UserDefinedRuleWithOneAction(Filter filter, Action action,
			NamedParametersBuilder namedParametersBuilder) {
		Preconditions.checkNotNull(filter);
		Preconditions.checkNotNull(action);
		this.filter = filter;
		this.action = action;
		this.namedParametersBuilder = namedParametersBuilder;
	}

	@Override
	public void pay(User customer, Product product) {
		Map<String, Object> namedParameters = namedParametersBuilder
				.build(customer, product);
		action.execute(customer, product);
	}

	@Override
	public boolean isApplicable(Product product) {
		return filter.isApplicable(product);
	}

}
