package com.kata.businessrules.helpers;

@FunctionalInterface
public interface Action {	
	void invoke() throws Exception;
}
