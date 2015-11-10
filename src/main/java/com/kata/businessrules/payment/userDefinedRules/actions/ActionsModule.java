package com.kata.businessrules.payment.userDefinedRules.actions;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ActionsModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder()
				.implement(Action.class, IssueReceiptAction.class)
				.build(IssueReceiptActionFactory.class));
		install(new FactoryModuleBuilder()
				.implement(Action.class, SendMessageAction.class)
				.build(SendMessageActionFactory.class));
	}

}
