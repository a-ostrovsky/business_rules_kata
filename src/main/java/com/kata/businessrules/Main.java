package com.kata.businessrules;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.kata.businessrules.contact.DummyContactModule;
import com.kata.businessrules.payment.userDefinedRules.actions.ActionsModule;
import com.kata.businessrules.payment.userDefinedRules.xmlParsing.XmlParsingModule;

public class Main {
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new ActionsModule(),
				new XmlParsingModule(), new MainModule(),
				new DummyContactModule());
		RuleEngineLoader<Document> loader = injector.getInstance(
				new Key<RuleEngineLoader<Document>>() {
				});		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new File("C:\\rules.xml"));
		loader.load(doc);
	}
}
