package com.kata.businessrules;

import com.google.inject.Guice;
import com.kata.businessrules.contact.DummyContactModule;
import com.kata.businessrules.payment.userDefinedRules.actions.ActionsModule;
import com.kata.businessrules.payment.userDefinedRules.xmlParsing.XmlParsingModule;

public class Main {
	public static void main(String[] args) {
		Guice.createInjector(
				new ActionsModule(),
				new XmlParsingModule(),				
				new DummyMainModule(),
				new DummyContactModule());
	}
}
