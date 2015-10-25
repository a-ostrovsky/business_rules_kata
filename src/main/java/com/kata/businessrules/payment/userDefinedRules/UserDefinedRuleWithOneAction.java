package com.kata.businessrules.payment.userDefinedRules;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;
import com.kata.businessrules.products.Product;

public class UserDefinedRuleWithOneAction implements PaymentBehavior {

	private Action action;
	private Filter filter;

	public UserDefinedRuleWithOneAction(Filter filter, Action action) {
		Preconditions.checkNotNull(filter);
		Preconditions.checkNotNull(action);
		this.filter = filter;
		this.action = action;		
	}

	@Override
	public void pay(User customer, Product product) {		
		action.execute(customer, product);
	}

	@Override
	public boolean isApplicable(Product product) {
		return filter.isApplicable(product);
	}

}
