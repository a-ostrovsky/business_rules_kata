package com.kata.businessrules.payment.userDefinedRules.actions;

public interface ActionFactory <T extends Action> {
	T create();
}
