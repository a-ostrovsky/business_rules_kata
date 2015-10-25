package com.kata.businessrules.payment.userDefinedRules;

import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.ActionFactory;

public class FixedActionFactory<T extends Action>
		implements ActionFactory<T> {

	private T fixedObject;

	public FixedActionFactory(T fixedObject) {
		this.fixedObject = fixedObject;
	}

	@Override
	public T create() {
		return fixedObject;
	}

}
